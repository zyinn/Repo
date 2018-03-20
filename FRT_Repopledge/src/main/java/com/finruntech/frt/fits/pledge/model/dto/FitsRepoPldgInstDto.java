package com.finruntech.frt.fits.pledge.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.finruntech.frt.fits.pledge.commons.annotation.ValidDbLength;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yinan.zhang on 2018/1/2.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoPldgInstDto {
    @NotNull(message = "投资组合非空")
    private String fPortfAcctSecu;//投资组合
    @NotNull(message = "会计分类非空")
    private String fAccountingType; //会计分类
    private String fExecutor;//交易员
    @NotNull(message = "交易对手非空")
    private String fCounterpartyId;//交易对手
    private String fCpTrader;//对手交易员
    private String fCpCashAcc;//对手资金账号
    @NotNull(message = "执行市场非空")
    private String fExecution;//执行市场
    @NotNull(message = "交易日期非空")
    private String fTradeDate;//交易日期
    @NotNull(message = "回购品种非空")
    private String fRepoCode;//标的回购代码
    @NotNull(message = "回购方向非空")
    private String fTradeDirection;//回购方向 正回购:B 逆回购：S
    @NotNull(message = "期限(天）非空")
    private long fPledgeTerm;//回购期限(天）
    @NotNull(message = "清算速度非空")
    private String fSettleSpeed;//清算速度
    @NotNull(message = "首次结算日期非空")
    private String fSettleDate1st;//首次结算日期
    @NotNull(message = "到期结算日期非空")
    private String fSettleDate2nd;//到期结算日期
    @NotNull(message = "回购面额非空")
    private BigDecimal fAmount;//投资指令回购面额
    @NotNull(message = "回购利率非空")
    private BigDecimal fRepoRate;//回购利率
    @NotNull(message = "到期还款面额非空")
    private BigDecimal fMatureAmount;//	投资指令到期还款面额
    @NotNull(message = "首次结算方式非空")
    private String fSettleType1st;//首次结算方式
    @NotNull(message = "到期结算方式非空")
    private String fSettleType2nd;//到期结算方式
    @ValidDbLength(message = "备注最大值为200字符", length = 200)
    private String fRemarks;//备注
    @NotNull(message = "交易金额非空")
    @DecimalMin(value = "0",message = "结算金额最值需大于0")
    @DecimalMax(value = "9999999999999999999999999999999.9999999999999999", message = "结算金额最大值9999999999999999999999999999999.9999999999999999")
    private BigDecimal fTrdSettleAmount;
    private String userId; //(服务端用于单据流水记录使用)

    //    @NotNull(message = "质押债券代码非空")
    private String fInstrument;//质押债券代码
    //    @NotNull(message = "质押债券的折扣率非空")
    private BigDecimal fPledgeRatio;//质押债券的折扣率

    private BigDecimal fTrdParValue; //标的债券的券面金额
    private BigDecimal fTrdFullPrice;//标的债券的全价

    private String fFormNum;//修改使用
    @Valid
    private List<FitsRepoPldgMgtEntity> pldgMgtList;//质押券


    //修改全字段
    @JSONField(name = "fFormId")
    private long fFormId;//记录ID
    @JSONField(name = "aprvFormNum")
    private String aprvFormNum;//对应审批单编号
    @JSONField(name = "fTrdAcct")
    private String fTrdAcct;//交易账户
    @JSONField(name = "fCustAcctSecu")
    private String fCustAcctSecu;//托管证券账户
    @JSONField(name = "fCustAcctCash")
    private String fCustAcctCash;//托管资金账户
    @JSONField(name = "fCount")
    private long fCount;//投资指令回购数量
    @JSONField(name = "fBegDate")
    private String fBegDate;//投资指令有效开始日期
    @JSONField(name = "fEndDate")
    private String fEndDate;//投资指令有效结束日期
    @JSONField(name = "fSubmitDate")
    private String fSubmitDate;//投资指令提交日期
    @JSONField(name = "fInitiator")
    private String fInitiator;//指令发起人
    @JSONField(name = "fQuoteType")
    private String fQuoteType;//报价方式
    @JSONField(name = "fAmountBalance")
    private BigDecimal fAmountBalance;//指令单资金余额
    @JSONField(name = "fInstStatus")
    private String fInstStatus;//指令状态
    @JSONField(name = "fTrdAi")
    private BigDecimal fTrdAi;//标的债券的单位应计利息
    @JSONField(name = "fTrdNetPrice")
    private BigDecimal fTrdNetPrice;//标的债券的净价
    @JSONField(name = "fTrdYtm")
    private BigDecimal fTrdYtm;//标的债券的到期收益率
    @JSONField(name = "fTrdTotolAi")
    private BigDecimal fTrdTotolAi;//总应计利息
    @JSONField(name = "fTrdBondPriceType")
    private String fTrdBondPriceType;//标的债券的净价或全价
    @JSONField(name = "auditCreatedBy")
    private String auditCreatedBy;//留痕字段-创建人
    @JSONField(name = "auditCreateTime")
    private String auditCreateTime;//留痕字段-创建时间
    @JSONField(name = "auditModifiedBy")
    private String auditModifiedBy;//留痕字段-修改人
    @JSONField(name = "auditModifiedTime")
    private String auditModifiedTime;//留痕字段-修改时间

    @JSONField(name = "repoRateDeviation")
    private BigDecimal repoRateDeviation;//回购利率偏离度
    @JSONField(name = "leaderApprFlg")
    private Boolean leaderApprFlg;//投资经理审批标志

}
