package com.finruntech.frt.fits.pledge.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/5.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsPledgeInstResultDto {
    @JSONField(name ="fInitiatorName")
    private String fInitiatorName; //指令发起人
    @JSONField(name ="executorName")
    private String executorName; //执行人
    @JSONField(name ="fTradeDirectionName")
    private String fTradeDirectionName; //交易方向显示名称
    @JSONField(name ="fPortfacctCashName")
    private String fPortfacctCashName; //投资组合

    @JSONField(name ="fFormId")
    private long fFormId;//记录ID

    @JSONField(name ="fFormNum")
    private String fFormNum;//指令单编号

    @JSONField(name ="aprvFormNum")
    private String aprvFormNum;//对应审批单编号

    @JSONField(name ="fTrdAcct")
    private String fTrdAcct;//交易账户

    @JSONField(name ="fPortfAcctSecu")
    private String fPortfAcctSecu;//组合证券账户

    @JSONField(name ="fPortfAcctCash")
    private String fPortfAcctCash;//组合资金账户

    @JSONField(name ="fCustAcctSecu")
    private String fCustAcctSecu;//托管证券账户
    @JSONField(name ="fCustAcctCash")
    private String fCustAcctCash;//托管资金账户

    @JSONField(name ="fRepoCode")
    private String fRepoCode;//标的回购代码

    @JSONField(name ="fInstrument")
    private String fInstrument;//质押债券代码

    @JSONField(name ="fExecution")
    private String fExecution;//执行市场

    @JSONField(name ="fSettleType1st")
    private String fSettleType1st;//首次结算方式

    @JSONField(name ="fSettleType2nd")
    private String fSettleType2nd;//到期结算方式

    @JSONField(name ="fCount")
    private long fCount;//投资指令回购数量

    @JSONField(name ="fRepoRate")
    private BigDecimal fRepoRate;//回购利率

    @JSONField(name ="fAmount")
    private BigDecimal fAmount;//投资指令回购面额

    @JSONField(name ="fMatureAmount")
    private BigDecimal fMatureAmount;//    投资指令到期还款面额

    @JSONField(name ="fBegDate")
    private String fBegDate;//投资指令有效开始日期

    @JSONField(name ="fEndDate")
    private String fEndDate;//投资指令有效结束日期

    @JSONField(name ="fSubmitDate")
    private String fSubmitDate;//投资指令提交日期

    @JSONField(name ="fInitiator")
    private String fInitiator;//指令发起人

    @JSONField(name ="fExecutor")
    private String fExecutor;//指令执行人

    @JSONField(name ="fTradeDirection")
    private String fTradeDirection;//交易方向

    @JSONField(name ="fAccountingType")
    private String fAccountingType;//会计分类

    @JSONField(name ="fCounterpartyId")
    private String fCounterpartyId;//交易对手

    @JSONField(name ="fCpTrader")
    private String fCpTrader;//对手交易员

    @JSONField(name ="fCpCashAcc")
    private String fCpCashAcc;//对手资金账号

    @JSONField(name ="fTradeDate")
    private String fTradeDate;//交易日期

    @JSONField(name ="fSettleDate1st")
    private String fSettleDate1st;//首次结算日期

    @JSONField(name ="fSettleDate2nd")
    private String fSettleDate2nd;//到期结算日期

    @JSONField(name ="fSettleSpeed")
    private String fSettleSpeed;//清算速度

    @JSONField(name ="fQuoteType")
    private String fQuoteType;//报价方式

    @JSONField(name ="fAmountBalance")
    private BigDecimal fAmountBalance;//指令单资金余额

    @JSONField(name ="fInstStatus")
    private String fInstStatus;//指令状态

    @JSONField(name ="fRemarks")
    private String fRemarks;//备注

    @JSONField(name ="fTrdAi")
    private BigDecimal fTrdAi;//标的债券的单位应计利息

    @JSONField(name ="fTrdFullPrice")
    private BigDecimal fTrdFullPrice;//标的债券的全价

    @JSONField(name ="fTrdParValue")
    private BigDecimal fTrdParValue;//标的债券的券面金额

    @JSONField(name ="fTrdNetPrice")
    private BigDecimal fTrdNetPrice;//标的债券的净价

    @JSONField(name ="fTrdYtm")
    private BigDecimal fTrdYtm;//标的债券的到期收益率

    @JSONField(name ="fTrdTotolAi")
    private BigDecimal fTrdTotolAi;//总应计利息

    @JSONField(name ="fTrdSettleAmount")
    private BigDecimal fTrdSettleAmount;//结算金额

    @JSONField(name ="fPledgeRatio")
    private BigDecimal fPledgeRatio;//质押债券的折扣率

    @JSONField(name ="fPledgeTerm")
    private long fPledgeTerm;//回购期限(天）

    @JSONField(name ="fTrdBondPriceType")
    private String fTrdBondPriceType;//标的债券的净价或全价

    @JSONField(name ="auditCreatedBy")
    private String auditCreatedBy;//留痕字段-创建人

    @JSONField(name ="auditCreateTime")
    private String auditCreateTime;//留痕字段-创建时间

    @JSONField(name ="auditModifiedBy")
    private String auditModifiedBy;//留痕字段-修改人
    @JSONField(name ="auditModifiedTime")
    private String auditModifiedTime;//留痕字段-修改时间
    @JSONField(name ="fProcessNum")
    private String fProcessNum;//审批流程编号
    @JSONField(name ="bmBondName")
    private String bmBondName;//债券名称
    @JSONField(name ="fInstStatusName")
    private String fInstStatusName;//指令状态

    @JSONField(name ="deCounterpartyName")
    private String deCounterpartyName;//交易对手名称

    @JSONField(name ="raRepoName")
    private String raRepoName;//回购名称

    @JSONField(name ="fExecutionName")
    private String fExecutionName;//执行市场名称

}
