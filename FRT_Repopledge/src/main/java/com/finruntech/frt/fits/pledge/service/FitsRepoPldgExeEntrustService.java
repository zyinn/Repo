package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.enums.*;
import com.finruntech.frt.fits.pledge.commons.util.BizUtil;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.commons.util.exception.ExceptionUtil;
import com.finruntech.frt.fits.pledge.commons.util.exception.FitsException;
import com.finruntech.frt.fits.pledge.converter.FitsRepoPldgMgtConverter;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgInstEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgOrdEntity;
import com.finruntech.frt.fits.pledge.model.dto.*;
import com.finruntech.frt.fits.pledge.repository.FitsAccCustodyMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgInstMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgOrdMapper;
import com.finruntech.frt.fits.pledge.repository.SelectSEQRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weihubin on 2018/1/2.
 * 指令执行、委托 service
 */
@Service
public class FitsRepoPldgExeEntrustService<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FitsRepoPldgInstMapper fitsRepoPldgInstMapper;
    @Autowired
    private FitsRepoPldgOrdMapper fitsRepoPldgOrdMapper;
    @Autowired
    private SystemStatusService systemStatusService;
    @Autowired
    private SelectSEQRepository selectSEQRepository;
    @Autowired
    private FitsAccCustodyMapper fitsAccCustodyMapper;
    @Autowired
    private FitsRepoPldgMgtService fitsRepoPldgMgtService;
    @Autowired
    private FitsRepoPldgMgtConverter fitsRepoPldgMgtConverter;
    /**
     * 指令执行查询
     * @param msg
     * @return
     */
    public PageInfo<?> exeInstQuery(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        RepoInstDto repoInstDto = JSONObject.toJavaObject(jsonObject, RepoInstDto.class);
        String dealExeStatus = jsonObject.getString("dealExeStatus");
        List<String> fInstStatusList = new ArrayList<>();
        if (InstExeStatus.Inst_notExed.getCode().equals(dealExeStatus)) { //4-审批通过,6-无需审批
            fInstStatusList.add(InstStatus.APPROVED.getCode());
            fInstStatusList.add(InstStatus.NOAPPROVAL.getCode());
        } else if (InstExeStatus.Inst_Exed.getCode().equals(dealExeStatus)) {
            fInstStatusList.add(InstStatus.EXECUTION.getCode());//执行中
        } else {
            fInstStatusList.add(InstStatus.APPROVED.getCode());
            fInstStatusList.add(InstStatus.NOAPPROVAL.getCode());
            fInstStatusList.add(InstStatus.EXECUTION.getCode());//执行中
        }
        repoInstDto.setFInstStatusList(fInstStatusList);
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        PageInfo<RepoInstDto> pageInfo = new PageInfo<>(fitsRepoPldgInstMapper.exeInstQuery(repoInstDto));
        return pageInfo;
    }
    /**
     * 指令执行
     * @param msg
     * @return
     */
    @Transactional
    public void exeInvstOper(String msg){
        JSONObject object = JSON.parseObject(msg);
        String fExecutor = object.getString("fExecutor");//原指定的执行人
        String userId = object.getString("userId");
        String psCashNum = object.getString("fPortfacctCash");
        List<String> portAcctCashList = (List<String>) object.get("portAcctCashList");
        if (!BizUtil.isPsCashNumAuth(psCashNum, portAcctCashList) ||
                (StringUtils.isNotBlank(fExecutor) && !userId.equals(fExecutor))) {//非空，只能执行人执行 或 具有
            throw new FitsException(ExceptionUtil.EerrInfo, "执行失败，没有执行权限!");
        }
        //修改指令状态为执行中 、执行人等信息
        FitsRepoPldgInstEntity fitsRepoPldgInstEntity = new FitsRepoPldgInstEntity();
        fitsRepoPldgInstEntity.setFInstStatus(InstStatus.EXECUTION.getCode());
        fitsRepoPldgInstEntity.setFExecutor(userId);
        fitsRepoPldgInstEntity.setAuditModifiedBy(userId);
        fitsRepoPldgInstEntity.setAuditModifiedTime(Utils.getCurrentTime());
        fitsRepoPldgInstEntity.setFFormNum( object.getString("fFormNum"));
        fitsRepoPldgOrdMapper.updateInvstStatus(fitsRepoPldgInstEntity);
    }

    /**
     * 指令委托查询
     * @param msg
     * @return
     */
    public PageInfo<?> entrustInvstQuery(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        RepoInstDto repoInstDto = JSONObject.toJavaObject(jsonObject, RepoInstDto.class);
        List<String> fInstStatusList = new ArrayList<>();
        fInstStatusList.add(OrderFStatus.EXECUTION.getCode());//执行中
        repoInstDto.setFInstStatusList(fInstStatusList);
        repoInstDto.setFExecutor(jsonObject.getString("userId"));//只查询执行人是当前登录id
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        PageInfo<RepoInstDto> pageInfo = new PageInfo<>(fitsRepoPldgInstMapper.exeInstQuery(repoInstDto));
        return pageInfo;
    }




    /**
     * 查询委托信息
     * @param msg
     * @return
     */
    public PageInfo<?> qryOrderlist(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        jsonObject.put("ordInvinstNum" , jsonObject.getString("fFormNum"));
        PageInfo<FitsRepoPldgOrdDto> pageInfo = new PageInfo<>(fitsRepoPldgOrdMapper.findRepoPldgOrd(jsonObject));
        return pageInfo;
    }

    /**
     * 委托操作
     * @param msg
     * @return
     */
    public Map<String, Object> addOrderInitInfo(String msg){
        Map<String, Object> rtnMap = new HashMap<>();
        try {
            JSONObject object = JSON.parseObject(msg);
            String fPortfacctCash = object.getString("fPortfacctCash");
            //1.托管资金、证券账号列表信息
            rtnMap.put("accCusCashList", fitsAccCustodyMapper.findAccCusCashByPortCash(fPortfacctCash));//查询本方机构-资金账号
            rtnMap.put("accCusSecuList", fitsAccCustodyMapper.findAccCusSecByPortCash(fPortfacctCash));//查询本方机构->证券账号
        }catch (Exception e){
            logger.error("repoPldgOrderOper orror" , e);
            rtnMap.put("errMsg", e.getLocalizedMessage());
        }
        return rtnMap;
    }


    /**
     * 银行间回购-> 新增委托，取消委托
     * @param oper
     * @param msg
     */
    @Transactional
    public void dealRepoPldgOrderOper(String oper, String msg) {
        JSONObject object = JSON.parseObject(msg);
        FitsRepoPldgOrdDto ordDto = object.toJavaObject(FitsRepoPldgOrdDto.class);
        RepoInstDto repoInstDto = object.toJavaObject(RepoInstDto.class);
        String userId = object.getString("userId");
        String fExecutor = repoInstDto.getFExecutor();
        String fFormNum = repoInstDto.getFFormNum();//指令编号
        String currentDate  = systemStatusService.currDate();
        List pldgMgtList = null;

        //验证是否是历史数据
        FitsPledgeInstResultDto dto = (FitsPledgeInstResultDto)fitsRepoPldgInstMapper.qryInstByFormNum(fFormNum);
        if(dto!=null && !"".equals(dto)){
            if(!(Utils.stringToDateString(dto.getFSubmitDate())).equals(currentDate))
                throw  new FitsException(ExceptionUtil.E5023,
                        ExceptionUtil.E5023.getExceptionInfoCN());
        }

        if("addOrder".equalsIgnoreCase(oper)){
            if(!userId.equals(fExecutor)){//非只能执行人委托
                throw  new FitsException(ExceptionUtil.EerrInfo, "委托失败，没有委托权限!");
            }
            //如果指令存在对应委托，无法继续委托
            int ordCount = fitsRepoPldgOrdMapper.ordCountByInstNum(fFormNum);
            if (ordCount > 0) {
                throw  new FitsException(ExceptionUtil.EerrInfo, "指令存在对应委托，无法继续委托!");
            }
            //新增委托信息
            initFitsExgOrderEntity(object, ordDto);
            String ordSeq = selectSEQRepository.getFitsSeq(Constants.ORDER_SEQ);

            ordDto.setOrdId(new BigDecimal(ordSeq));
            ordDto.setOrdNum(systemStatusService.getOrdNum(currentDate, ordSeq));//委托编号
            ordDto.setOrdInvinstNum(fFormNum);
            ordDto.setOrdInitiator(userId);//委托单发起人
            ordDto.setAuditCreatedby(userId);
            ordDto.setAuditCreatetime(Utils.getCurrentTime());
            ordDto.setOrdDate(currentDate);//委托日期
            ordDto.setOrdTime(Utils.getCurrentTimeHhmmss());//委托时间
            ordDto.setOrdStatus(OrdStatus.ORDERED.getCode());//委托状态->已委托
            fitsRepoPldgOrdMapper.insertRepoPldgOrd(ordDto);
            //登记委托质押券信息
            pldgMgtList = ordDto.getPldgMgtList();
            if(pldgMgtList !=null && pldgMgtList.size() > 0){
                fitsRepoPldgMgtService.saveRepoPldgMgtEntity(fitsRepoPldgMgtConverter.
                        repoPldgMgtConverter(pldgMgtList, "ORD", ordDto.getOrdNum()
                                , ordDto.getOrdCustacctSecu(), ordDto.getOrdCustacctCash()));
            }
        }else if("cancelOrder".equalsIgnoreCase(oper)){//取消委托
            if(!userId.equals(fExecutor)){//非只能执行人委托
                throw  new FitsException(ExceptionUtil.EerrInfo, "取消委托失败，没有取消权限!");
            }
            //如果已全部成交，无法撤销委托
            String ordStatus = ordDto.getOrdStatus();
            if(OrdStatus.ALLTRAN.getCode().equals(ordStatus)){
                throw new FitsException(ExceptionUtil.EerrInfo, "已全部成交，无法撤销委托!");
            }
            //删除委托单信息
            fitsRepoPldgOrdMapper.delRepoPldgOrdByOrdNum(ordDto.getOrdNum());
            pldgMgtList = ordDto.getPldgMgtList();
            if(pldgMgtList !=null && pldgMgtList.size() > 0){
                //删除登记委托质押券信息
                fitsRepoPldgMgtService.deleteRepoPldgMgtEntity(ordDto.getPldgMgtList().get(0));
            }
        }
    }


    /**
     * 初始化委托单信息
     * @param object
     * @param ordEntity
     */
    private void initFitsExgOrderEntity(JSONObject object, FitsRepoPldgOrdEntity ordEntity) {
        ordEntity.setOrdSetDate1st(object.getString("fSettleDate1st"));//	首次结算日期
        ordEntity.setOrdSetDate2nd(object.getString("fSettleDate2nd"));//	到期结算日期
        ordEntity.setOrdSetType1st(object.getString("fSettleType1st"));//	首次结算方式
        ordEntity.setOrdSetType2nd(object.getString("fSettleType2nd"));//	到期结算方式
        ordEntity.setOrdTradeDirection(object.getString("fTradeDirection"));//交易方向
        ordEntity.setOrdAccountingType(object.getString("fAccountingType"));//会计分类
        ordEntity.setOrdCounterpartyId(object.getString("fCounterpartyId"));//交易易对手
        ordEntity.setOrdCpTrader(object.getString("fCpTrader"));//交对手交易员
        ordEntity.setOrdCpCashacc(object.getString("fCpCashacc"));//对手资金账号
        ordEntity.setOrdSettleSpeed(object.getString("fSettleSpeed"));
        ordEntity.setOrdQuoteType(object.getString("fQuoteType"));//报价方式
        ordEntity.setOrdExecutionMarket(object.getString("fExecution"));//执行市场
        ordEntity.setOrdInstrument(object.getString("fInstrument"));//	质押债券代码
        ordEntity.setOrdInstrumentName(object.getString("bName"));//金融工具名称 TODO
        ordEntity.setOrdRepoCode(object.getString("fRepoCode"));//标的回购代码
        ordEntity.setOrdRepoRate(object.getBigDecimal("fRepoRate").divide(new BigDecimal(100)));//回购利率
        ordEntity.setOrdCount(object.getBigDecimal("fCount"));//	投资指令回购数量
        ordEntity.setOrdAmount(object.getBigDecimal("fAmount").multiply(new BigDecimal(10000)));//投资指令回购面额
        ordEntity.setOrdMatureAmount(object.getBigDecimal("fMatureAmount"));//	投资指令到期还款金额
//        ordEntity.setOrdPledgeRatio(object.getBigDecimal("fPledgeRatio").divide(new BigDecimal(100)));//	质押债券的折扣率
        ordEntity.setOrdPledgeTerm(object.getBigDecimal("fPledgeTerm"));//回购期限(天）
        ordEntity.setOrdSettleAmount(object.getBigDecimal("fTrdSettleAmount"));//	委托单交易金额

        //交易金额(tradeAmt)、资金账户(accCusCash)、应计利息(trdAi)、总应计利息(trdTotolAi)
        ordEntity.setOrdTotalAi(object.getBigDecimal("fTrdTotolAi"));//委托单总应计利息

        // ORD_CUSTACCT_CASH	本方托管资金帐号
        // ORD_CUSTACCT_SECU	托管证券账户
        // ORD_PORTACCT_SECU	组合证券帐号
        // ORD_PORTACCT_CASH	组合资金帐号
        JSONObject accCusSecuObj = object.getJSONObject("accCusSecu");
        if(accCusSecuObj !=null){
            ordEntity.setOrdCustacctSecu(accCusSecuObj.getString("hsAccNumber"));//托管证券帐号
        }
        JSONObject accCusCashObj = object.getJSONObject("accCusCash");
        if(accCusCashObj !=null){
            ordEntity.setOrdCustacctCash(accCusCashObj.getString("hcAccNumber"));//托管资金帐号
        }
        ordEntity.setOrdPortacctSecu(object.getString("fPortfAcctSecu"));// 组合证券帐号
        ordEntity.setOrdPortacctCash(object.getString("fPortfAcctCash"));// 组合资金帐号
    }




    /**
     * 委托单打印
     * @param msg
     * @return
     */
    public Map<String, Object> orderPrint(String msg){
        Map<String, Object> rtnMap = new HashMap<>();
        try {
            JSONObject jsonObject = JSON.parseObject(msg);
            String ordNum = jsonObject.getString("ordNum");
            FitsRepoPldgOrdDto fitsRepoPldgOrdDto =
                    (FitsRepoPldgOrdDto) fitsRepoPldgOrdMapper.findRepoPldgOrdByOrdNum(ordNum);
            String  entName = fitsRepoPldgOrdDto.getEntName(); //托管账户名称对应的机构号
            //交易方向
            String ordTradeDirection = fitsRepoPldgOrdDto.getOrdTradeDirection();
            //交易对手
            String cpEntName = fitsRepoPldgOrdDto.getCpEntName();
            String cpTrader = fitsRepoPldgOrdDto.getOrdCpTrader();//登记的是人名
            String ordInitiatorName = fitsRepoPldgOrdDto.getOrdInitiatorName();//委托发起人
            if(RepoDirection.Repo_BUY.getCode().equals(ordTradeDirection)){//正回购信息
                rtnMap.put("inEntName", entName);
                rtnMap.put("inTrader", ordInitiatorName);

                rtnMap.put("outEntName", cpEntName);
                rtnMap.put("outTrader", cpTrader);
            }else if(RepoDirection.Repo_SELL.getCode().equals(ordTradeDirection)){//逆回购信息
                rtnMap.put("inEntName", cpEntName);
                rtnMap.put("inTrader", cpTrader);

                rtnMap.put("outEntName", entName);
                rtnMap.put("outTrader", ordInitiatorName);
            }
            rtnMap.putAll(JSON.parseObject(JSON.toJSONString(fitsRepoPldgOrdDto)));
            rtnMap.put("reponseType", "success");
        }catch (Exception e){
            logger.error("repoPldgOrderOper orror" , e);
            rtnMap.put("errMsg", e.getLocalizedMessage());
        }
        return rtnMap;
    }



    /**
     * 交易录入，委托查询
     * @param msg
     * @return
     */
    public PageInfo<?> tradeEntryQryOrder(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        jsonObject.put("ordStatus" , OrdStatus.ORDERED.getCode()); //只查询委托中的
        PageInfo<FitsRepoPldgOrdDto> pageInfo = new PageInfo<>(fitsRepoPldgOrdMapper.tradeEntryQryOrder(jsonObject));
        return pageInfo;
    }

    /**
     * 交易录入根据委托单编号查询
     * @param msg
     * @return
     */
    public FitsRepoPldgTransEntryDto tradeEnterDetailQry(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        String ordNum= jsonObject.getString("ordNum");
        return (FitsRepoPldgTransEntryDto) fitsRepoPldgOrdMapper.tradeEnterDetailQry(ordNum);
    }
}
