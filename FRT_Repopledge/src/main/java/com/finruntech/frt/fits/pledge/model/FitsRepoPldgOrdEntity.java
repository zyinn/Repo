package com.finruntech.frt.fits.pledge.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by lenovo on 2017/12/27.
 */
@Entity
@Setter
@Getter
@Table(name = "FRT_FITS_REPOPLDG_ORD")
public class FitsRepoPldgOrdEntity {
    @Column(name="ORD_ID")
    @JSONField(name ="ordId")
    private BigDecimal ordId;
    @Column(name="ORD_NUM")
    @JSONField(name ="ordNum")
    private String ordNum;
    @Column(name="ORD_INVINST_NUM")
    @JSONField(name ="ordInvinstNum")
    private String ordInvinstNum;
    @Column(name="ORD_DATE")
    @JSONField(name ="ordDate")
    private String ordDate;
    @Column(name="ORD_TIME")
    @JSONField(name ="ordTime")
    private String ordTime;
    @Column(name="ORD_COUNT")
    @JSONField(name ="ordCount")
    private BigDecimal ordCount;
    @Column(name="ORD_PRICE")
    @JSONField(name ="ordPrice")
    private BigDecimal ordPrice;//委托价格-净价
    @Column(name="ORD_DIRTYPRICE")
    @JSONField(name ="ordDirtyPrice")
    private BigDecimal ordDirtyPrice; //委托价格-全价
    @Column(name="ORD_AMOUNT")
    @JSONField(name ="ordAmount")
    private BigDecimal ordAmount; //委托单回购金额
    @Column(name="ORD_MATURE_AMOUNT")
    @JSONField(name ="ordMatureAmount")
    private BigDecimal ordMatureAmount;  //委托单到期还款面额
    @Column(name="ORD_CUSTACCT_CASH")
    @JSONField(name ="ordCustacctCash")
    private String ordCustacctCash;
    @Column(name="ORD_CUSTACCT_SECU")
    @JSONField(name ="ordCustacctSecu")
    private String ordCustacctSecu;
    @Column(name="ORD_PORTACCT_SECU")
    @JSONField(name ="ordPortacctSecu")
    private String ordPortacctSecu;
    @Column(name="ORD_PORTACCT_CASH")
    @JSONField(name ="ordPortacctCash")
    private String ordPortacctCash;
    @Column(name="ORD_SET_DATE1ST")
    @JSONField(name ="ordSetDate1st")
    private String ordSetDate1st;
    @Column(name="ORD_SET_DATE2ND")
    @JSONField(name ="ordSetDate2nd")
    private String ordSetDate2nd;
    @Column(name="ORD_SET_TYPE1ST")
    @JSONField(name ="ordSetType1st")
    private String ordSetType1st;
    @Column(name="ORD_SET_TYPE2ND")
    @JSONField(name ="ordSetType2nd")
    private String ordSetType2nd;
    @Column(name="ORD_REPO_RATE")
    @JSONField(name ="ordRepoRate")
    private BigDecimal ordRepoRate; //委托单回购利率
    @Column(name="ORD_PLEDGE_RATIO")
    @JSONField(name ="ordPledgeRatio")
    private BigDecimal ordPledgeRatio; //质押债券的折扣率
    @Column(name="ORD_PLEDGE_TERM")
    @JSONField(name ="ordPledgeTerm")
    private BigDecimal ordPledgeTerm; //	回购期限(天）
    @Column(name="ORD_INITIATOR")
    @JSONField(name ="ordInitiator")
    private String ordInitiator;
    @Column(name="ORD_EXECUTOR")
    @JSONField(name ="ordExecutor")
    private String ordExecutor;
    @Column(name="ORD_TRADE_DIRECTION")
    @JSONField(name ="ordTradeDirection")
    private String ordTradeDirection;
    @Column(name="ORD_ACCOUNTING_TYPE")
    @JSONField(name ="ordAccountingType")
    private String ordAccountingType;
    @Column(name="ORD_COUNTERPARTY_ID")
    @JSONField(name ="ordCounterpartyId")
    private String ordCounterpartyId;
    @Column(name="ORD_CP_TRADER")
    @JSONField(name ="ordCpTrader")
    private String ordCpTrader;
    @Column(name="ORD_CP_CASHACC")
    @JSONField(name ="ordCpCashacc")
    private String ordCpCashacc;
    @Column(name="ORD_SETTLE_SPEED")
    @JSONField(name ="ordSettleSpeed")
    private String ordSettleSpeed;
    @Column(name="ORD_QUOTE_TYPE")
    @JSONField(name ="ordQuoteType")
    private String ordQuoteType;
    @Column(name="ORD_EXECUTION_MARKET")
    @JSONField(name ="ordExecutionMarket")
    private String ordExecutionMarket;
    @Column(name="ORD_REPO_CODE")
    @JSONField(name ="ordRepoCode")
    private String ordRepoCode;
    @Column(name="ORD_INSTRUMENT")
    @JSONField(name ="ordInstrument")
    private String ordInstrument;
    @Column(name="ORD_INSTRUMENT_NAME")
    @JSONField(name ="ordInstrumentName")
    private String ordInstrumentName;
    @Column(name="ORD_TOTAL_AI")
    @JSONField(name ="ordTotalAi")
    private BigDecimal ordTotalAi;
    @Column(name="ORD_YTM")
    @JSONField(name ="ordYtm")
    private BigDecimal ordYtm;
    @Column(name="ORD_SETTLE_AMOUNT")
    @JSONField(name ="ordSettleAmount")
    private BigDecimal ordSettleAmount;
    @Column(name="ORD_EXERCISE_YIELD")
    @JSONField(name ="ordExerciseYield")
    private BigDecimal ordExerciseYield;
    @Column(name="ORD_AMOUNT_BALANCE")
    @JSONField(name ="ordAmountBalance")
    private BigDecimal ordAmountBalance;
    @Column(name="ORD_STATUS")
    @JSONField(name ="ordStatus")
    private String ordStatus;
    @Column(name="AUDIT_CREATEDBY")
    @JSONField(name ="auditCreatedby")
    private String auditCreatedby;
    @Column(name="AUDIT_CREATETIME")
    @JSONField(name ="auditCreatetime")
    private String auditCreatetime;
    @Column(name="AUDIT_MODIFIEDBY")
    @JSONField(name ="auditModifiedby")
    private String auditModifiedby;
    @Column(name="AUDIT_MODIFIEDTIME")
    @JSONField(name ="auditModifiedtime")
    private String auditModifiedtime;
}
