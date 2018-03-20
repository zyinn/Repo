package com.finruntech.frt.fits.pledge.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2017/12/27.
 */
@Entity
@Table(name = "FRT_FITS_REPOPLDG_INST")
@Setter
@Getter
public class FitsRepoPldgInstEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "F_FORM_ID")
    @JSONField(name ="fFormId")
    private long fFormId;//记录ID
    @Id
    @Column(name = "F_FORM_NUM")
    @JSONField(name ="fFormNum")
    private String fFormNum;//指令单编号
    @Column(name = "APRVFORM_NUM")
    @JSONField(name ="aprvFormNum")
    private String aprvFormNum;//对应审批单编号
    @Column(name = "F_TRDACCT")
    @JSONField(name ="fTrdAcct")
    private String fTrdAcct;//交易账户
    @Column(name = "F_PORTFACCT_SECU")
    @JSONField(name ="fPortfAcctSecu")
    private String fPortfAcctSecu;//组合证券账户
    @Column(name = "F_PORTFACCT_CASH")
    @JSONField(name ="fPortfAcctCash")
    private String fPortfAcctCash;//组合资金账户
    @Column(name = "F_CUSTACCT_SECU")
    @JSONField(name ="fCustAcctSecu")
    private String fCustAcctSecu;//托管证券账户
    @JSONField(name ="fCustAcctCash")
    @Column(name = "F_CUSTACCT_CASH")
    private String fCustAcctCash;//托管资金账户
    @Column(name = "F_REPO_CODE")
    @JSONField(name ="fRepoCode")
    private String fRepoCode;//标的回购代码
    @Column(name = "F_INSTRUMENT")
    @JSONField(name ="fInstrument")
    private String fInstrument;//质押债券代码
    @Column(name = "F_EXECUTION")
    @JSONField(name ="fExecution")
    private String fExecution;//执行市场
    @Column(name = "F_SETTLE_TYPE1ST")
    @JSONField(name ="fSettleType1st")
    private String fSettleType1st;//首次结算方式
    @Column(name = "F_SETTLE_TYPE2ND")
    @JSONField(name ="fSettleType2nd")
    private String fSettleType2nd;//到期结算方式
    @Column(name = "F_COUNT")
    @JSONField(name ="fCount")
    private long fCount;//投资指令回购数量
    @Column(name = "F_REPO_RATE")
    @JSONField(name ="fRepoRate")
    private BigDecimal fRepoRate;//回购利率
    @Column(name = "F_AMOUNT")
    @JSONField(name ="fAmount")
    private BigDecimal fAmount;//投资指令回购面额
    @Column(name = "F_MATURE_AMOUNT")
    @JSONField(name ="fMatureAmount")
    private BigDecimal fMatureAmount;//    投资指令到期还款面额
    @Column(name = "F_BEG_DATE")
    @JSONField(name ="fBegDate")
    private String fBegDate;//投资指令有效开始日期
    @Column(name = "F_END_DATE")
    @JSONField(name ="fEndDate")
    private String fEndDate;//投资指令有效结束日期
    @Column(name = "F_SUBMIT_DATE")
    @JSONField(name ="fSubmitDate")
    private String fSubmitDate;//投资指令提交日期
    @Column(name = "F_INITIATOR")
    @JSONField(name ="fInitiator")
    private String fInitiator;//指令发起人
    @Column(name = "F_EXECUTOR")
    @JSONField(name ="fExecutor")
    private String fExecutor;//指令执行人
    @Column(name = "F_TRADE_DIRECTION")
    @JSONField(name ="fTradeDirection")
    private String fTradeDirection;//交易方向
    @Column(name = "F_ACCOUNTING_TYPE")
    @JSONField(name ="fAccountingType")
    private String fAccountingType;//会计分类
    @Column(name = "F_COUNTERPARTY_ID")
    @JSONField(name ="fCounterpartyId")
    private String fCounterpartyId;//交易对手
    @Column(name = "F_CP_TRADER")
    @JSONField(name ="fCpTrader")
    private String fCpTrader;//对手交易员
    @Column(name = "F_CP_CASHACC")
    @JSONField(name ="fCpCashAcc")
    private String fCpCashAcc;//对手资金账号
    @Column(name = "F_TRADE_DATE")
    @JSONField(name ="fTradeDate")
    private String fTradeDate;//交易日期
    @Column(name = "F_SETTLE_DATE1ST")
    @JSONField(name ="fSettleDate1st")
    private String fSettleDate1st;//首次结算日期
    @Column(name = "F_SETTLE_DATE2ND")
    @JSONField(name ="fSettleDate2nd")
    private String fSettleDate2nd;//到期结算日期
    @Column(name = "F_SETTLE_SPEED")
    @JSONField(name ="fSettleSpeed")
    private String fSettleSpeed;//清算速度
    @Column(name = "F_QUOTE_TYPE")
    @JSONField(name ="fQuoteType")
    private String fQuoteType;//报价方式
    @Column(name = "F_AMOUNT_BALANCE")
    @JSONField(name ="fAmountBalance")
    private BigDecimal fAmountBalance;//指令单资金余额
    @Column(name = "F_INST_STATUS")
    @JSONField(name ="fInstStatus")
    private String fInstStatus;//指令状态
    @Column(name = "F_REMARKS")
    @JSONField(name ="fRemarks")
    private String fRemarks;//备注
    @Column(name = "F_TRD_AI")
    @JSONField(name ="fTrdAi")
    private BigDecimal fTrdAi;//标的债券的单位应计利息
    @Column(name = "F_TRD_FULL_PRICE")
    @JSONField(name ="fTrdFullPrice")
    private BigDecimal fTrdFullPrice;//标的债券的全价
    @Column(name = "F_TRD_PAR_VALUE")
    @JSONField(name ="fTrdParValue")
    private BigDecimal fTrdParValue;//标的债券的券面金额
    @Column(name = "F_TRD_NET_PRICE")
    @JSONField(name ="fTrdNetPrice")
    private BigDecimal fTrdNetPrice;//标的债券的净价
    @Column(name = "F_TRD_YTM")
    @JSONField(name ="fTrdYtm")
    private BigDecimal fTrdYtm;//标的债券的到期收益率
    @Column(name = "F_TRD_TOTOL_AI")
    @JSONField(name ="fTrdTotolAi")
    private BigDecimal fTrdTotolAi;//总应计利息
    @Column(name = "F_TRD_SETTLE_AMOUNT")
    @JSONField(name ="fTrdSettleAmount")
    private BigDecimal fTrdSettleAmount;//结算金额
    @Column(name = "F_PLEDGE_RATIO")
    @JSONField(name ="fPledgeRatio")
    private BigDecimal fPledgeRatio;//质押债券的折扣率
    @Column(name = "F_PLEDGE_TERM")
    @JSONField(name ="fPledgeTerm")
    private long fPledgeTerm;//回购期限(天）
    @Column(name = "F_TRD_BONDPRICE_TYPE")
    @JSONField(name ="fTrdBondPriceType")
    private String fTrdBondPriceType;//标的债券的净价或全价
    @Column(name = "AUDIT_CREATEDBY")
    @JSONField(name ="auditCreatedBy")
    private String auditCreatedBy;//留痕字段-创建人
    @Column(name = "AUDIT_CREATETIME")
    @JSONField(name ="auditCreateTime")
    private String auditCreateTime;//留痕字段-创建时间
    @Column(name = "AUDIT_MODIFIEDBY")
    @JSONField(name ="auditModifiedBy")
    private String auditModifiedBy;//留痕字段-修改人
    @Column(name = "AUDIT_MODIFIEDTIME")
    @JSONField(name ="auditModifiedTime")
    private String auditModifiedTime;//留痕字段-修改时间

}
