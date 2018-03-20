package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.commons.enums.InstStatus;
import com.finruntech.frt.fits.pledge.commons.enums.OrderFStatus;
import com.finruntech.frt.fits.pledge.commons.enums.RepoDirection;
import com.finruntech.frt.fits.pledge.commons.util.XMLUtil;
import com.finruntech.frt.fits.pledge.commons.util.exception.ExceptionUtil;
import com.finruntech.frt.fits.pledge.commons.util.exception.FitsException;
import com.finruntech.frt.fits.pledge.converter.FitsPledgeInstConverter;
import com.finruntech.frt.fits.pledge.dispatcher.MQDispatcher;
import com.finruntech.frt.fits.pledge.dispatcher.RiskMGTDispatcher;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgInstEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.FitsSystemConfigEntity;
import com.finruntech.frt.fits.pledge.model.dto.*;
import com.finruntech.frt.fits.pledge.model.dto.approval.BpmRedisDto;
import com.finruntech.frt.fits.pledge.model.dto.approval.ProcessParamDto;
import com.finruntech.frt.fits.pledge.model.visible_attrs;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgInstMapper;
import com.finruntech.frt.fits.pledge.repository.SystemStatusMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by yinan.zhang on 2017/12/27.
 */
@Service
public class FitsRepoPldgInstService<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FitsRepoPldgInstMapper fitsPledgeInstMapper;
    @Autowired
    private MQDispatcher mqDispatcher;
    @Autowired
    private FitsRepoApprovalService repoApproval;
    @Autowired
    private FitsRepoPldgMgtService repoPldgMgtService;
    @Autowired
    private FitsPledgeInstConverter pledgeInstConverter;
    @Autowired
    private FitsRepoPldgBondMgtService repoPldgBondMgtService;
    @Autowired
    private SystemStatusService systemStatusService;
    @Autowired
    private RiskMGTDispatcher riskMGTDispatcher;
    @Autowired
    private SystemStatusMapper systemStatusMapper;

    private String instMessage;

    public int savePledgeInst(T entity, FitsRepoPldgQueryInstDto dto, List<FitsRepoPldgMgtEntity> list) {
        int instSave = saveFitsPledgeInst((FitsRepoPldgInstEntity) entity);
        PageInfo<FitsPledgeInstResultDto> pageInfo = (PageInfo<FitsPledgeInstResultDto>) queryPledgeInst(dto);
        FitsPledgeInstResultDto resultDto = pageInfo.getList().get(0);
        if (!list.isEmpty()) {
            List<FitsRepoPldgMgtEntity> listEntity = pledgeInstConverter.repoPldgMgtConverter(list, resultDto);
            repoPldgMgtService.saveRepoPldgMgtEntity(listEntity);
        }
        if (instSave == 0)
            throw new FitsException(ExceptionUtil.E6001, "savePledgeInst " + ExceptionUtil.E6001.getErrorInfoCN());
        return 1;
    }

    @Transactional
    public int saveFitsPledgeInst(FitsRepoPldgInstEntity entity) {
        return fitsPledgeInstMapper.saveFitsPledgeInst(entity);
    }

    public int submitPledgeInst(FitsRepoPldgInstEntity entity, FitsRepoPldgQueryInstDto dto, FitsRepoPldgInstDto fitsRepoPldgInstDto) {
        List<FitsRepoPldgMgtEntity> list = fitsRepoPldgInstDto.getPldgMgtList();
        int instSave = saveFitsPledgeInst(entity);
        if (instSave != 0) {
            PageInfo<FitsPledgeInstResultDto> pageInfo = (PageInfo<FitsPledgeInstResultDto>) queryPledgeInst(dto);
            FitsPledgeInstResultDto resultDto = pageInfo.getList().get(0);
            if(list !=null &&  list.size() > 0 ){
                List<FitsRepoPldgMgtEntity> listEntity = pledgeInstConverter.repoPldgMgtConverter(list, resultDto);
                repoPldgMgtService.saveRepoPldgMgtEntity(listEntity);
                //验证债券可用数
                checkRepoBondAvailable(entity, list);
            }
            if (resultDto != null) {
                ProcessParamDto proParamDto = new ProcessParamDto();
                proParamDto.setFormId(resultDto.getAprvFormNum());
                proParamDto.setUserId(resultDto.getFInitiator());
                getProcessParamDto(fitsRepoPldgInstDto.getRepoRateDeviation(), resultDto.getFAmount(),
                        fitsRepoPldgInstDto.getLeaderApprFlg(), proParamDto);

                mqDispatcher.send(JSON.toJSONString(proParamDto));
            }
//            checkApprovalStatus();
            return 1;
        }
        if (instSave == 0)
            throw new FitsException(ExceptionUtil.E6001, "submitPledgeInst " + ExceptionUtil.E6001.getErrorInfoCN());

        return 1;
    }

    private int checkApprovalStatus(String aprvFormNum) {
        while (true) {
            if(instMessage!=null && !"".equals(instMessage)){
                BpmRedisDto bpmRedisDto = JSON.parseObject(instMessage, BpmRedisDto.class);
                instMessage="";
                if(bpmRedisDto.getFormNum().equals(aprvFormNum)&& bpmRedisDto.getReqStatus().equals("200")){
                    return 1;
                }
            }
        }
    }

    private void checkRepoBondAvailable(FitsRepoPldgInstEntity instEntity, List<FitsRepoPldgMgtEntity> mgtlist) {
        if(instEntity.getFTradeDirection().equals(RepoDirection.Repo_BUY.getCode())){
            mgtlist.forEach(entity -> {
                FitsRepoPldgBondMgtEntityDto MgtEntity = pledgeInstConverter.converterFitsRepoPldgBondMgtEntityDto(instEntity, entity);
                checkRepoPldgBondMgt(MgtEntity);
            });
        }
    }

    /**
     * 价格偏离度请求审批
     *
     * @param repoRateDeviation
     * @param variablesObj
     * @return
     */
    private JSONObject variablesObj(BigDecimal repoRateDeviation, JSONObject variablesObj) {
        JSONObject object = new JSONObject();
        object.put("rmTypeMaJor", "D");
        object.put("rmTypeMinor", "D2");
        String rtnMsg = riskMGTDispatcher.sendPost("getRiskRule", object.toJSONString());
        JSONObject rtnObj = JSON.parseObject(rtnMsg);
        String rmWarning = rtnObj.getString("rmWarning");
        BigDecimal rmWarningVal = new BigDecimal(rmWarning.replaceAll("%", ""));
        rmWarningVal = rmWarningVal.divide(new BigDecimal(100));
        variablesObj.put("repoRateDeviationValue", repoRateDeviation);//计算出的偏离度
        variablesObj.put("repoRateDeviation", rmWarningVal);//计算出的偏离度
        return variablesObj;
    }


    @Transactional
    private int updateRepoBondAvailable(FitsRepoPldgInstEntity instEntity, List<FitsRepoPldgMgtEntity> mgtlist) {
        if(instEntity.getFTradeDirection().equals(RepoDirection.Repo_BUY.getCode())){
            List<FitsRepoPldgBondMgtEntityDto> list = new ArrayList<>();
            mgtlist.forEach(entity -> {
                FitsRepoPldgBondMgtEntityDto MgtEntity = pledgeInstConverter.converterFitsRepoPldgBondMgtEntityDto(instEntity, entity);
                checkRepoPldgBondMgt(MgtEntity);
                list.add(MgtEntity);
            });
            return repoPldgBondMgtService.updateRepoBondAvailable(list);
        }
        return 0;
    }

    private void checkRepoPldgBondMgt(FitsRepoPldgBondMgtEntityDto MgtEntity) {
        FitsRepoPldgBondMgtEntityDto bondMgtEntityDto = repoPldgBondMgtService.checkRepoPldgBondMgt(MgtEntity);
        int i = bondMgtEntityDto.getBmBondAvailAble().compareTo(MgtEntity.getBmBondAvailAble());
        if (i == -1) {
            throw new FitsException(ExceptionUtil.E6002, "债券：" + MgtEntity.getBmBondCode() + ExceptionUtil.E6002.getErrorInfoCN());
        }
    }

    @Transactional
    public int updateFitsPledgeInst(FitsRepoPldgInstEntity entity, List<FitsRepoPldgMgtEntity> list) {
        int instUpdate = fitsPledgeInstMapper.updateFitsPledgeInst(entity);
        FitsRepoPldgMgtDto dto = new FitsRepoPldgMgtDto();
        //由于修改之后回购代码可能会变(只根据指令编号删除对应质押券信息)
        dto.setSubJectNum(entity.getFFormNum());
        repoPldgMgtService.deleteRepoPldgMgtEntity(dto);
        if(!list.isEmpty()){
            repoPldgMgtService.saveRepoPldgMgtEntity(list);
        }
        if (instUpdate == 0)
            throw new FitsException(ExceptionUtil.E6001, "updateFitsPledgeInst " + ExceptionUtil.E6001.getErrorInfoCN());
        return 1;
    }

    public PageInfo<?> queryPledgeInst(FitsRepoPldgQueryInstDto dto) {
        Map<String, String> map = new HashMap<>();
        map.put("fPortfacctCash", dto.getFPortfAcctSecu());
        map.put("startDate", dto.getStartDate());
        map.put("endDate", dto.getEndDate());
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getOrderColumn() + " " + dto.getOrderBy());
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize(), sb.toString());
        return new PageInfo<>(fitsPledgeInstMapper.queryFitsPledgeInst(map));
    }

    @Transactional
    public int updatePledgeCancelInst(FitsRepoCancelPldgInstDto dto) {
        ProcessParamDto dto1 = new ProcessParamDto();
        BeanUtils.copyProperties(dto, dto1);
        return repoApproval.repoCancelProcess(dto1);
    }

    @Transactional
    public void updateBandMgtAvailableInst() {
        try {
            String currentDate = systemStatusService.currDate();
            List<String> list = fitsPledgeInstMapper.clrQueryResultAprvFormNum(currentDate);
            list.forEach(i -> {
                logger.info("currentDate:" + currentDate + ",AprvFormNum" + i);
                //设置可用券数量
                updateBandMgtAvailableByAprFormNum(i);
            });
        } catch (Exception e) {
            logger.error("归还质押债券数量失败！", e);
        }

    }

    public void updateBandMgtAvailableByAprFormNum(String aprvFormNum) {
        FitsPledgeInstResultDto resultDto = (FitsPledgeInstResultDto) fitsPledgeInstMapper.qryInstByAprvFormNum(aprvFormNum);
        FitsRepoPldgInstEntity instEntity = pledgeInstConverter.converterFitsRepoPldgInstEntity(resultDto);
        FitsRepoPldgMgtDto mgtDto = pledgeInstConverter.converterFitsRepoPldgMgtDto(resultDto);
        List<FitsRepoPldgMgtEntity> repoPldgMgt = repoPldgMgtService.findPldgMgtEntity(mgtDto);
        setRepoBondAvailable(instEntity, repoPldgMgt);
    }

    private int setRepoBondAvailable(FitsRepoPldgInstEntity instEntity, List<FitsRepoPldgMgtEntity> mgtlist) {
        List<FitsRepoPldgBondMgtEntityDto> list = new ArrayList<>();
        mgtlist.forEach(entity -> {
            FitsRepoPldgBondMgtEntityDto MgtEntity = pledgeInstConverter.converterFitsRepoPldgBondMgtEntityDto(instEntity, entity);
//            //验证bondMgt
//            checkRepoPldgBondMgt(MgtEntity);
            list.add(MgtEntity);
        });
        return repoPldgBondMgtService.updateRepoBondAvailableGiveBack(list);
    }

    @Transactional
    public int submitPledgeInstStatus(FitsRepoCancelPldgInstDto dto) {
        if (dto.getFInstStatus().equals(OrderFStatus.APPROVALROLLBACK.getCode())) {
            ProcessParamDto dto1 = getProcessParamDto(dto);
            //指令提交，请求bpm参数
            getProcessParamDto(dto.getRepoRateDeviation(), dto.getFAmount(),dto.getLeaderApprFlg(), dto1);
            return repoApproval.repoRestartProcess(dto1);
        } else {
            long l = System.currentTimeMillis();
            //发送MQ
            ProcessParamDto proParamDto = new ProcessParamDto();
            proParamDto.setFormId(dto.getFormId());
            proParamDto.setUserId(dto.getUserId());
            //指令提交，请求bpm参数
            getProcessParamDto(dto.getRepoRateDeviation(), dto.getFAmount(), dto.getLeaderApprFlg(), proParamDto);

            mqDispatcher.send(JSON.toJSONString(proParamDto));
            logger.info("start send MQ:"+(System.currentTimeMillis()-l)+"ms");
            //从redis中获取审批状态验证
            return checkApprovalStatus(dto.getFormId());
        }
    }

    public void receiveMessage(String message) {
        //收到通道的消息之后执行的方法
        logger.info(message);
        instMessage=message;
    }

    //指令提交设置可用券
    public void setBandMgtAvailableByAprvFormNum(String aprvFormNum) {
        FitsPledgeInstResultDto resultDto = (FitsPledgeInstResultDto) fitsPledgeInstMapper.qryInstByAprvFormNum(aprvFormNum);
        FitsRepoPldgInstEntity instEntity = pledgeInstConverter.converterFitsRepoPldgInstEntity(resultDto);
        FitsRepoPldgMgtDto mgtDto = pledgeInstConverter.converterFitsRepoPldgMgtDto(resultDto);
        List<FitsRepoPldgMgtEntity> repoPldgMgt = repoPldgMgtService.findPldgMgtEntity(mgtDto);
        if(repoPldgMgt !=null && repoPldgMgt.size() > 0){
            updateRepoBondAvailable(instEntity, repoPldgMgt);
        }
    }

    private ProcessParamDto getProcessParamDto(FitsRepoCancelPldgInstDto dto) {
        ProcessParamDto dto1 = new ProcessParamDto();
        dto1.setFormId(dto.getFormId());
        dto1.setUserId(dto.getUserId());
        dto1.setProcessId(dto.getProcessId());
        JSONObject object = new JSONObject();
        object.put("bMoney", dto.getFAmount());
        dto1.setKey("bRepoProcess");
        dto1.setVariables(object.toJSONString());
        return dto1;
    }


    /**
     *  指令提交，请求bpm参数
     * @param leaderApprFlg
     * @param fAmount
     * @param proParamDto
     * @return
     */
    private ProcessParamDto getProcessParamDto(BigDecimal repoRateDeviation, BigDecimal fAmount, Boolean leaderApprFlg, ProcessParamDto proParamDto) {
        //回购利率偏离度XML
        visible_attrs visibleAttr = new visible_attrs();
        visibleAttr.setRepoRateDeviation(repoRateDeviation);//回购利率偏离度
        String repoRateDeviationXml = XMLUtil.convertToXml(visibleAttr);//visible_attrs生成对应的 xml
        proParamDto.setFDisplayattr(repoRateDeviationXml);
        JSONObject bpmParm = new JSONObject();
        bpmParm.put("bMoney", fAmount);
        bpmParm.put("leaderApprFlg", leaderApprFlg == null ? false:leaderApprFlg);
        proParamDto.setKey("bRepoProcess");
        proParamDto.setVariables(bpmParm.toJSONString());
        return proParamDto;
    }


    public String repoTermLimitDays() {
        FitsSystemConfigEntity fitsSystemConfigEntity = (FitsSystemConfigEntity) systemStatusMapper
                .selectSystemConfig("RepoPldgRepoTermLimit");
        return fitsSystemConfigEntity.getConfigValue();
    }
}
