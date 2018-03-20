package com.finruntech.frt.fits.pledge.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lenovo on 2018/1/8.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoPldgDealDto{
    @JSONField(name ="deDealId")
    private BigDecimal deDealId;    //自增长记录序号
    @JSONField(name ="deNum")
    private String deNum;    //成交编号
    @JSONField(name ="deOrderNum")
    private String deOrderNum;    //委托编号
    @JSONField(name ="deExeIdNum")
    private String deExeIdNum;    //成交回报交易序号
    @JSONField(name ="dePortfolioSecu")
    private String dePortfolioSecu;    //组合证券帐号
    @JSONField(name ="dePortfolioCash")
    private String dePortfolioCash;    //组合资金帐号
    @JSONField(name ="deHostSecu")
    private String deHostSecu;    //托管证券帐号
    @JSONField(name ="deHostCash")
    private String deHostCash;    //托管资金帐号
    @JSONField(name ="deRepoCode")
    private String deRepoCode;    //回购代码
    @JSONField(name ="deDealDate")
    private String deDealDate;    //成交日期
    @JSONField(name ="deDealTime")
    private String deDealTime;    //成交时间
    @JSONField(name ="deRepoCount")
    private BigDecimal deRepoCount;    //成交的回购数量
    @JSONField(name ="deRepoAmount")
    private BigDecimal deRepoAmount;    //成交的回购金额
    @JSONField(name ="deOpenEndFlg")
    private String deOpenEndFlg;    //OPEN END标识
    @JSONField(name ="deAcrIntstFlg")
    private String deAcrIntstFlg;    //应计利息标识
    @JSONField(name ="deRepoInstFlg")
    private String deRepoInstFlg;    //回购利息接受时间标识
    @JSONField(name ="deCountParty")
    private String deCountParty;    //FRT_FITS_ENTERPRISE.ENT_CODE
    @JSONField(name ="deTradeDirection")
    private String deTradeDirection;    //交易方向
    @JSONField(name ="deCpTrader")
    private String deCpTrader;    //对手交易员
    @JSONField(name ="deCpCashAcc")
    private String deCpCashAcc;    //对手资金账号
    @JSONField(name ="deExecutionMarket")
    private String deExecutionMarket;    //执行市场
    @JSONField(name ="dePledgedBond")
    private String dePledgedBond;    //质押债券/借券代码
    @JSONField(name ="dePledgedBondName")
    private String dePledgedBondName;    //质押债券/借券名称
    @JSONField(name ="deManual")
    private String deManual;    //是否手工录入交易
    @JSONField(name ="deManualDate")
    private String deManualDate;    //手工录入日期
    @JSONField(name ="deDealConfirmation")
    private String deDealConfirmation;    //是否成交确认过（中债登、清算所）
    @JSONField(name ="deRepoStartYmd")
    private String deRepoStartYmd;    //开始日期
    @JSONField(name ="deRepoEndYmd")
    private String deRepoEndYmd;    //结束日期
    @JSONField(name ="deSettleDate1st")
    private String deSettleDate1st;    //首次结算日期
    @JSONField(name ="deSettleDate2nd")
    private String deSettleDate2nd;    //到期结算日期
    @JSONField(name ="dePledgedInst")
    private BigDecimal dePledgedInst;    //质押贷款偿付利息金額（合计值）
    @JSONField(name ="deCollateralFee")
    private BigDecimal deCollateralFee;    //借券费用金額（合计值）
    @JSONField(name ="deCollateralRate")
    private BigDecimal deCollateralRate;    //借券担保金率
    @JSONField(name ="deCollateralAmount")
    private BigDecimal deCollateralAmount;    //借券担保金额
    @JSONField(name ="deTax")
    private BigDecimal deTax;    //印花税
    @JSONField(name ="deTransferFee1")
    private BigDecimal deTransferFee1;    //过户费
    @JSONField(name ="deSettleFee")
    private BigDecimal deSettleFee;    //结算费
    @JSONField(name ="deTradeFee")
    private BigDecimal deTradeFee;    //交易规费
    @JSONField(name ="deTransferFee2")
    private BigDecimal deTransferFee2;    //经手费
    @JSONField(name ="deSecurityManageFee")
    private BigDecimal deSecurityManageFee;    //证管费
    @JSONField(name ="deOtherFee")
    private BigDecimal deOtherFee;    //其他费
    @JSONField(name ="deMatchordStatus")
    private String deMatchordStatus;    //与委托单匹配状况（1：匹配成功；0：匹配不成功）
    @JSONField(name ="deCancelFlg")
    private String deCancelFlg;    //取消标识
    @JSONField(name ="deBrokerFlg")
    private String deBrokerFlg;    //Broker标识
    @JSONField(name ="auditCreatedBy")
    private String auditCreatedBy;    //留痕字段-创建人
    @JSONField(name ="auditCreateTime")
    private String auditCreateTime;    //留痕字段-创建时间
    @JSONField(name ="auditModifiedBy")
    private String auditModifiedBy;    //留痕字段-修改人
    @JSONField(name ="auditModifiedTime")
    private String auditModifiedTime;    //留痕字段-修改时间
    @JSONField(name ="psName")
    private String psName;//投资组合名称
    @JSONField(name ="fInitiatorName")
    private String fInitiatorName;//提交人
    @JSONField(name ="raRepoName")
    private String raRepoName;//回购品种
    @JSONField(name ="fSettleSpeed")
    private String fSettleSpeed;//清算速度

    @JSONField(name ="deRepoRate")
    private BigDecimal deRepoRate;// 成交，	回购利率
    @JSONField(name ="deMatureAmount")
    private BigDecimal deMatureAmount;// 成交，到期还款金额
    @JSONField(name ="deSettleAmount")
    private BigDecimal deSettleAmount;//成交 交易金额
    @JSONField(name ="dePledgeTerm")
    private BigDecimal dePledgeTerm; //成交  回购天数
    @JSONField(name ="deCounterpartyId")
    private String deCounterpartyId;//交易对手id
    @JSONField(name ="deTradeDirectionName")
    private String deTradeDirectionName;//交易方向名称
    @JSONField(name ="deCounterpartyName")
    private String deCounterpartyName;//交易对手名称

    @JSONField(name ="fFormNum")
    private String fFormNum;//指令编号
    @JSONField(name ="deMatchordStatusName")
    private String deMatchordStatusName;//匹配状态
    @JSONField(name ="psAcctClassName")
    private String psAcctClassName;//会计分类
    @JSONField(name ="fTradeDate")
    private String fTradeDate;//交易日期
    @JSONField(name ="pldgMgtList")
    private List<FitsRepoPldgMgtEntity> pldgMgtList;//质押券
}
