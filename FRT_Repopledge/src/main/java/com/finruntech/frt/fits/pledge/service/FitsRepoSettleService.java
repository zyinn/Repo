package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.commons.enums.RepoDirection;
import com.finruntech.frt.fits.pledge.commons.enums.SStatus;
import com.finruntech.frt.fits.pledge.commons.enums.SprocType;
import com.finruntech.frt.fits.pledge.commons.util.BizUtil;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.commons.util.exception.ExceptionUtil;
import com.finruntech.frt.fits.pledge.commons.util.exception.FitsException;
import com.finruntech.frt.fits.pledge.dispatcher.MQDispatcher;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgDealEntity;
import com.finruntech.frt.fits.pledge.model.FitsSettleInstEntity;
import com.finruntech.frt.fits.pledge.model.dto.*;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgDealMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoSettleInstMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2018/1/10.
 */
@Service
public class FitsRepoSettleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FitsRepoSettleInstMapper fitsRepoSettleInstMapper;
    @Autowired
    private FitsRepoPldgDealMapper fitsRepoPldgDealMapper;
    @Autowired
    private MQDispatcher mqDispatcher;
    @Autowired
    private FitsRepoPldgMgtService fitsRepoPldgMgtService;

    /**
     * 结算指令查询
     * @param jsonObject
     * @return
     */
    public PageInfo<?> settleInstQry(JSONObject jsonObject) {
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        PageInfo<FitsRepoSettleInstDto> pageInfo = new PageInfo<>(fitsRepoSettleInstMapper.settleInstQry(jsonObject));
        return pageInfo;
    }

    /**
     * 结算确认
     * @param msg
     */
    public void settleInstComfirm(String msg){
        JSONObject jsonObject = JSON.parseObject(msg);
        //1.根据sId，查找结算指令
        FitsSettleInstEntity fitsSettleInstEntity = (FitsSettleInstEntity)
                fitsRepoSettleInstMapper.qrySettleInstByPk(jsonObject);
        String sStatus= SStatus.CONFIRMED.getCode();//已确认
        if(sStatus.equals(fitsSettleInstEntity.getSStatus())){ //不能重复确认
            throw  new FitsException(ExceptionUtil.EerrInfo, "指令已确认!");
        }
        String portCash = fitsSettleInstEntity.getPortCash();
        List<String> portAcctCashList = (List<String>) jsonObject.get("portAcctCashList");
        if (!BizUtil.isPsCashNumAuth(portCash, portAcctCashList)) {//非空，只能执行人执行 或 具有
            throw new FitsException(ExceptionUtil.EerrInfo, "结算确认失败，没有执行权限!");
        }
        //2. 业务要求确认之后发送mq，计算日间持仓(到期结算，非交易指令不请求mq)
        if(SprocType.DAYTIME.getCode().equals(fitsSettleInstEntity.getSType())){
            FitsRepoSettleInstDto fitsRepoSettleInstDto =  new FitsRepoSettleInstDto();
            BeanUtils.copyProperties(fitsSettleInstEntity, fitsRepoSettleInstDto);
            fitsRepoSettleInstDto.setSProcType(SprocType.DAYTIME.getCode()); //结算类型,日间1,日终2
            fitsRepoSettleInstDto.setSStatus(sStatus);
            //如果是正回购，需要查找质押券
            List pldgMgtList = null;
            if(RepoDirection.Repo_BUY.getCode().equals(fitsSettleInstEntity.getSsecuDirection())){
                FitsRepoPldgMgtDto fitsRepoPldgMgtDto = new FitsRepoPldgMgtDto();
                fitsRepoPldgMgtDto.setPRepoCode(fitsSettleInstEntity.getSInstrument());
                fitsRepoPldgMgtDto.setSubJectType("DEAL");
                fitsRepoPldgMgtDto.setSubJectNum(jsonObject.getString("deNum"));
                pldgMgtList = fitsRepoPldgMgtService.findRepoPldgMgtEntity(fitsRepoPldgMgtDto);
            }
            fitsRepoSettleInstDto.setPldgMgtList(pldgMgtList);
            String sendSettleInstMsg = JSON.toJSONString(fitsRepoSettleInstDto);
            logger.info("repo sendSettleInstMsg:<"  + sendSettleInstMsg +">");
            //发送到持仓mq
            mqDispatcher.sendSettleInst(sendSettleInstMsg);
        }
        //3.更新结算表、成交表确认状态
        jsonObject.put("sStatus", sStatus);
        jsonObject.put("deDealConfirmation", sStatus);
        updateSettleDealStatus(jsonObject);

    }


    /**
     * 到期结算金额调整
     * @param msg
     */
    public void matureAmountModify(String msg){
        //修改结算指令金额
        JSONObject jsonObject = JSON.parseObject(msg);
        fitsRepoSettleInstMapper.updateSettleInst(jsonObject);
    }






    /**
     * 更新结算指令，已确认
     * 更新成交表，已确认成交
     * @param jsonObject
     */
    @Transactional
    public void updateSettleDealStatus(JSONObject jsonObject) {
        fitsRepoSettleInstMapper.updateSettleInst(jsonObject);
        //由于deal表中  DE_DEAL_CONFIRMATION	是否成交确认过（中债登、清算所）
        FitsRepoPldgDealEntity fitsRepoPldgDealEntity = jsonObject.toJavaObject(FitsRepoPldgDealEntity.class);
        fitsRepoPldgDealEntity.setAuditModifiedTime(Utils.getCurrentTime());
        fitsRepoPldgDealMapper.updateRepoPldgDeal(fitsRepoPldgDealEntity);
    }


    public PageInfo<?> nibRepoPldgQuery(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        PageInfo<FitsRepoPldgQryDto> pageInfo = new PageInfo<>(fitsRepoSettleInstMapper.nibRepoPldgQuery(jsonObject));
        return pageInfo;
    }
}
