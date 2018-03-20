package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.enums.MatchOrdStatus;
import com.finruntech.frt.fits.pledge.commons.enums.OrdStatus;
import com.finruntech.frt.fits.pledge.commons.enums.*;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.converter.FitsRepoPldgMgtConverter;
import com.finruntech.frt.fits.pledge.dispatcher.MQDispatcher;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgDealEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgOrdEntity;
import com.finruntech.frt.fits.pledge.model.FitsSettleInstEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsPageBaseDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgDealDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgOrdDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgQueryInstDto;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgDealMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgOrdMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoSettleInstMapper;
import com.finruntech.frt.fits.pledge.repository.SelectSEQRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weihubin on 2018/1/8.
 */
@Service
public class FitsRepoDealService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FitsRepoPldgDealMapper fitsRepoPldgDealMapper;
    @Autowired
    private FitsRepoPldgOrdMapper fitsRepoPldgOrdMapper;
    @Autowired
    private SelectSEQRepository selectSEQRepository;
    @Autowired
    private FitsRepoSettleInstMapper fitsRepoSettleInstMapper;
    @Autowired
    private SystemStatusService systemStatusService;
    @Autowired
    private MQDispatcher mqDispatcher;
    @Autowired
    private FitsRepoPldgMgtService fitsRepoPldgMgtService;
    @Autowired
    private FitsRepoPldgMgtConverter fitsRepoPldgMgtConverter;


    /**
     * 交易录入提交
     * @param msg
     */
    public void tradeEntryAubmit(String msg){
        mqDispatcher.sendTrans(msg);
    }

    public void repopldgMatchTrans(String msg) {
        try {
            JSONObject object = JSON.parseObject(msg);

            logger.error("recieve repopldgMatchTrans message:" + "<" + msg + ">");

            FitsRepoPldgDealDto fitsRepoPldgDealDto = this.saveRepoPldgDeal(object);

            this.matchSettleRepopldgDeal(fitsRepoPldgDealDto);

        } catch (Exception e) {
            logger.error("parse repopldgMatchTrans message  error:", e);
        }
    }

    /**
     * 保存成交回报信息
     * @param object FitsRepoPldgDealDto
     * @return FitsRepoPldgDealDto
     */
    private FitsRepoPldgDealDto saveRepoPldgDeal(JSONObject object) {
        FitsRepoPldgDealDto fitsRepoPldgDealDto = object.toJavaObject(FitsRepoPldgDealDto.class);
        String currentDate = systemStatusService.currDate();
        if(DealManual.MANUAL.getCode().equalsIgnoreCase(fitsRepoPldgDealDto.getDeManual())) {//人工-交易补录
            // 结算确认是，更新成交表的成交状态DE_DEAL_CONFIRMATION
            fitsRepoPldgDealDto.setDeManualDate(currentDate);
            //  deRepoRate;// 成交，	回购利率
            //  deMatureAmount;// 成交，到期还款金额
            //  deSettleAmount;//成交 交易金额
            //  dePledgeTerm; //成交  回购天数
            //fitsRepoPldgDealDto.setDeRepoRate(object.getBigDecimal("ordRepoRate").divide(new BigDecimal(100)));
            fitsRepoPldgDealDto.setDeRepoAmount(object.getBigDecimal("ordSettlAmount"));//成交的回购金额	= 交易金额
            fitsRepoPldgDealDto.setDePledgedInst(object.getBigDecimal("ordTotalAi"));//质押贷款偿付利息金額（合计值）
            fitsRepoPldgDealDto.setDeCounterpartyId(object.getString("ordCounterpartyId"));
        }
        fitsRepoPldgDealDto.setDeDealDate(currentDate);
        fitsRepoPldgDealDto.setDeDealTime(Utils.getCurrentTimeHhmmss());

        fitsRepoPldgDealDto.setAuditCreatedBy("auto");
        fitsRepoPldgDealDto.setAuditCreateTime(Utils.getCurrentTime());
        String deSeq = selectSEQRepository.getFitsSeq(Constants.DEALBOND_SEQ);
        fitsRepoPldgDealDto.setDeDealId(new BigDecimal(deSeq));
        fitsRepoPldgDealDto.setDeNum(SystemStatusService.getDeNum(currentDate, deSeq));//成交编号

        FitsRepoPldgDealEntity fitsRepoPldgDealEntity = new FitsRepoPldgDealEntity();
        BeanUtils.copyProperties(fitsRepoPldgDealDto, fitsRepoPldgDealEntity);
        fitsRepoPldgDealMapper.insertRepoPldgDeal(fitsRepoPldgDealEntity);
        List pldgMgtList = fitsRepoPldgDealDto.getPldgMgtList();
        if(pldgMgtList !=null && pldgMgtList.size() > 0){
            fitsRepoPldgMgtService.saveRepoPldgMgtEntity(fitsRepoPldgMgtConverter.
                    repoPldgMgtConverter(pldgMgtList, "DEAL", fitsRepoPldgDealDto.getDeNum()
                            , fitsRepoPldgDealDto.getDeHostSecu(), fitsRepoPldgDealDto.getDeHostCash()));
        }
        return fitsRepoPldgDealDto;
    }

    /**
     * 成交回报，匹配处理
     * @param fitsRepoPldgDealDto
     */
    @Transactional
    public void matchSettleRepopldgDeal(FitsRepoPldgDealDto fitsRepoPldgDealDto) {
        fitsRepoPldgDealDto.setDeMatchordStatus(MatchOrdStatus.match_fail.getCode());
        //1.根据匹配条件，开始匹配
        FitsRepoPldgOrdEntity matchOrderEntity = matchOrder(fitsRepoPldgDealDto);
        //2.匹配成功后委托单处理，修改委托单全部委托 、登记结算表、生成结算指令
        if (matchOrderEntity != null) {
            fitsRepoPldgDealDto.setDeOrderNum(matchOrderEntity.getOrdNum());//关联匹配编号
            fitsRepoPldgDealDto.setDeMatchordStatus(MatchOrdStatus.match_succ.getCode());
            matchOrderEntity.setOrdStatus(OrdStatus.ALLTRAN.getCode());
            fitsRepoPldgOrdMapper.updateRepoPldgOrd(matchOrderEntity);
            saveSettleRepoPldgInst(fitsRepoPldgDealDto);
        }
        //3.更新成交表匹配状态
        fitsRepoPldgDealMapper.updateRepoPldgDeal(fitsRepoPldgDealDto);

    }


    /**
     * 根据以下条件匹配:
     * 托管证券账号、首次结算日期、到期结算日期、交易金额、到期还款金额、回购代码、回购方向、交易对手
     * @param fitsRepoPldgDealDto
     * @return FitsRepoPldgOrdEntity
     */
    private FitsRepoPldgOrdEntity matchOrder(FitsRepoPldgDealDto fitsRepoPldgDealDto) {
        FitsRepoPldgOrdEntity qryOrder = new FitsRepoPldgOrdEntity();
        qryOrder.setOrdStatus(OrdStatus.ORDERED.getCode());//只查询委托中的
        qryOrder.setOrdCustacctSecu(fitsRepoPldgDealDto.getDeHostSecu());
        qryOrder.setOrdSetDate1st(fitsRepoPldgDealDto.getDeSettleDate1st());
        qryOrder.setOrdSetDate2nd(fitsRepoPldgDealDto.getDeSettleDate2nd());
        BigDecimal deRepoAmount = fitsRepoPldgDealDto.getDeRepoAmount();
        qryOrder.setOrdSettleAmount(deRepoAmount);//成交的回购金额	= 交易金额
        BigDecimal dePledgedInst = fitsRepoPldgDealDto.getDePledgedInst();
        qryOrder.setOrdMatureAmount(deRepoAmount.add(dePledgedInst));//结算金额
        qryOrder.setOrdTradeDirection(fitsRepoPldgDealDto.getDeTradeDirection());
        qryOrder.setOrdRepoCode(fitsRepoPldgDealDto.getDeRepoCode());
        qryOrder.setOrdCounterpartyId(fitsRepoPldgDealDto.getDeCounterpartyId());//交易对手
        List<FitsRepoPldgOrdEntity> matchOrderList = fitsRepoPldgOrdMapper.dealMatchOrder(qryOrder);
        if (matchOrderList == null || matchOrderList.size() == 0) {
            return null;
        } else { //取第一条记录
            return matchOrderList.get(0);
        }
    }


    /**
     * 登记银行间回购结算表
     * @param fitsRepoPldgDealDto
     */
    private void  saveSettleRepoPldgInst( FitsRepoPldgDealDto fitsRepoPldgDealDto){
        FitsSettleInstEntity fitsSettleInstEntity =  new FitsSettleInstEntity();
        String instSeq = selectSEQRepository.getFitsSeq(Constants.SETL_INST_SEQ);
        fitsSettleInstEntity.setSId(new BigDecimal(instSeq));
        fitsSettleInstEntity.setSType(InstType.TRADE_INST.getCode());//交易指令
        fitsSettleInstEntity.setDealId(fitsRepoPldgDealDto.getDeDealId());
        fitsSettleInstEntity.setSInstrument(fitsRepoPldgDealDto.getDeRepoCode());//	质押债券/借券代码
        //金融工具的产品类别，对应下行数据表的MARKETINDICATOR
        fitsSettleInstEntity.setSInstrumentType("RepoPldg"); //TODO
        fitsSettleInstEntity.setSetDate(fitsRepoPldgDealDto.getDeSettleDate1st()); //首次结算日期
        fitsSettleInstEntity.setSetDateReal(fitsRepoPldgDealDto.getDeSettleDate1st());

        String deTradeDirection = fitsRepoPldgDealDto.getDeTradeDirection();
        fitsSettleInstEntity.setSsecuDirection(deTradeDirection);//证券结算方向：买入，卖出，冻结，解冻，融入，融出

        String scashDirection = ""; //资金结算方向：进，出，冻结，解冻

        //正回购，资金方向先进（首次结算）后出（到期结算），逆回购，资金方向先出（首次结算）后进（到期结算）
        //首次结算
        if(RepoDirection.Repo_BUY.getCode().equals(deTradeDirection)){//买入
            scashDirection = ScashDirection.SCASH_ENTER.getCode(); //进
        }else if(RepoDirection.Repo_SELL.getCode().equals(deTradeDirection)){//卖出
            scashDirection =  ScashDirection.SCASH_OUT.getCode(); //出
        }
        fitsSettleInstEntity.setScashDirection(scashDirection);
//        PORT_CASH	投资组合资金帐户
//        HOST_CASH	托管资金账户
//        PORT_SECU	投资组合证券帐户
//        HOST_SECU	托管证券账户
        fitsSettleInstEntity.setHostSecu(fitsRepoPldgDealDto.getDeHostSecu());
        fitsSettleInstEntity.setHostCash(fitsRepoPldgDealDto.getDeHostCash());
        fitsSettleInstEntity.setPortSecu(fitsRepoPldgDealDto.getDePortfolioSecu());
        fitsSettleInstEntity.setPortCash(fitsRepoPldgDealDto.getDePortfolioCash());
//        SCASH_AMOUNT	资金结算金额
//        SSECU_VOLUME	证券结算数量
//        S_STATUS	确认状态,指令状态未确认、已确认
//        S_PROC_STATUS	结算指令已结算处理、未结算处理
        fitsSettleInstEntity.setScashAmount(fitsRepoPldgDealDto.getDeRepoAmount());//结算金额= 交易金额
        fitsSettleInstEntity.setSsecuVolume(fitsRepoPldgDealDto.getDeRepoCount());

        fitsSettleInstEntity.setSStatus(SStatus.UNCONFIRMED.getCode());//未确认 ，需要人工确认
        fitsSettleInstEntity.setSProcStatus(SProcStatus.UNSETTLED.getCode());//未结算处理
        fitsSettleInstEntity.setDealCounterpartyNum(fitsRepoPldgDealDto.getDeCountParty());


        fitsRepoSettleInstMapper.insertRepoSettleInst(fitsSettleInstEntity);
        //2017-11-28 业务要求确认之后发送mq，计算日间持仓
//        FitsSettleInstResultDto fitsSettleInstResultDto =  new FitsSettleInstResultDto();
//
//        BeanUtils.copyProperties(fitsSettleInstEntity, fitsSettleInstResultDto);
//
//        fitsSettleInstResultDto.setsProcType(SprocType.DAYTIME.getCode()); //结算类型,日间1,日终2
//
//        String sendPositonMsg = JSON.toJSONString(fitsSettleInstResultDto);
//
//        logger.info("sendPositonMsg:<"  + sendPositonMsg +">");
//        //发送到持仓mq
//        instructionDispatcher.sendPosition(sendPositonMsg);
    }

    public PageInfo<?> findRepoDealService(FitsRepoPldgQueryInstDto dto) {
        Map<String, String> map = new HashMap<>();
        map.put("dePortfolioCash", dto.getFPortfAcctSecu());
        map.put("startDate", dto.getStartDate());
        map.put("endDate", dto.getEndDate());
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getOrderColumn() + " " + dto.getOrderBy());
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize(), sb.toString());
        return new PageInfo<>(fitsRepoPldgDealMapper.findDealEntity(map));
    }


    /**
     * 交易匹配->成交列表
     * @param msg FitsPageBaseDto
     * @return list FitsRepoPldgDealDto
     */
    public PageInfo<?> matchDealList(String  msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        PageInfo<FitsRepoPldgDealDto> pageInfo = new PageInfo<>(fitsRepoPldgDealMapper.findDealEntity(jsonObject));
        return pageInfo;
    }

    /**
     * 交易匹配->委托列表
     * @param msg FitsPageBaseDto
     * @return list FitsRepoPldgOrdDto
     */
    public PageInfo<?> matchOrderList(String  msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        FitsPageBaseDto fitsPageBaseDto = JSONObject.toJavaObject(jsonObject, FitsPageBaseDto.class);
        StringBuffer orderStr = new StringBuffer(fitsPageBaseDto.getOrderColumn() + " " + fitsPageBaseDto.getOrderBy());
        PageHelper.startPage(fitsPageBaseDto.getPageNum(), fitsPageBaseDto.getPageSize(), orderStr.toString());
        PageInfo<FitsRepoPldgOrdDto> pageInfo = new PageInfo<>(fitsRepoPldgOrdMapper.findRepoPldgOrd(jsonObject));
        return pageInfo;
    }



}
