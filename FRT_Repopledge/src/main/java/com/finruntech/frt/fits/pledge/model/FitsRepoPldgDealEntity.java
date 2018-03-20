package com.finruntech.frt.fits.pledge.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/8.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FRT_FITS_REPOPLDG_DEAL")
public class FitsRepoPldgDealEntity {
    @Column(name = "DE_DEAL_ID")
    @JSONField(name ="deDealId")
    private BigDecimal deDealId;    //自增长记录序号
    @Column(name = "DE_NUM")
    @JSONField(name ="deNum")
    private String deNum;    //成交编号
    @Column(name = "DE_ORDER_NUM")
    @JSONField(name ="deOrderNum")
    private String deOrderNum;    //委托编号
    @Column(name = "DE_EXEID_NUM")
    @JSONField(name ="deExeIdNum")
    private String deExeIdNum;    //成交回报交易序号
    @Column(name = "DE_PORTFOLIO_SECU")
    @JSONField(name ="dePortfolioSecu")
    private String dePortfolioSecu;    //组合证券帐号
    @Column(name = "DE_PORTFOLIO_CASH")
    @JSONField(name ="dePortfolioCash")
    private String dePortfolioCash;    //组合资金帐号
    @Column(name = "DE_HOST_SECU")
    @JSONField(name ="deHostSecu")
    private String deHostSecu;    //托管证券帐号
    @Column(name = "DE_HOST_CASH")
    @JSONField(name ="deHostCash")
    private String deHostCash;    //托管资金帐号
    @Column(name = "DE_REPO_CODE")
    @JSONField(name ="deRepoCode")
    private String deRepoCode;    //回购代码
    @Column(name = "DE_DEAL_DATE")
    @JSONField(name ="deDealDate")
    private String deDealDate;    //成交日期
    @Column(name = "DE_DEAL_TIME")
    @JSONField(name ="deDealTime")
    private String deDealTime;    //成交时间
    @Column(name = "DE_REPO_COUNT")
    @JSONField(name ="deRepoCount")
    private BigDecimal deRepoCount;    //成交的回购数量
    @Column(name = "DE_REPO_AMOUNT")
    @JSONField(name ="deRepoAmount")
    private BigDecimal deRepoAmount;    //成交的回购金额
    @Column(name = "DE_OPENEND_FLG")
    @JSONField(name ="deOpenEndFlg")
    private String deOpenEndFlg;    //OPEN END标识
    @Column(name = "DE_ACRINTST_FLG")
    @JSONField(name ="deAcrIntstFlg")
    private String deAcrIntstFlg;    //应计利息标识
    @Column(name = "DE_REPOINST_FLG")
    @JSONField(name ="deRepoInstFlg")
    private String deRepoInstFlg;    //回购利息接受时间标识
    @Column(name = "DE_COUNTPARTY")
    @JSONField(name ="deCountParty")
    private String deCountParty;    //FRT_FITS_ENTERPRISE.ENT_CODE
    @Column(name = "DE_TRADE_DIRECTION")
    @JSONField(name ="deTradeDirection")
    private String deTradeDirection;    //交易方向
    @Column(name = "DE_CP_TRADER")
    @JSONField(name ="deCpTrader")
    private String deCpTrader;    //对手交易员
    @Column(name = "DE_CP_CASHACC")
    @JSONField(name ="deCpCashAcc")
    private String deCpCashAcc;    //对手资金账号
    @Column(name = "DE_EXECUTION_MARKET")
    @JSONField(name ="deExecutionMarket")
    private String deExecutionMarket;    //执行市场
    @Column(name = "DE_PLEDGED_BOND")
    @JSONField(name ="dePledgedBond")
    private String dePledgedBond;    //质押债券/借券代码
    @Column(name = "DE_PLEDGED_BONDNAME")
    @JSONField(name ="dePledgedBondName")
    private String dePledgedBondName;    //质押债券/借券名称
    @Column(name = "DE_MANUAL")
    @JSONField(name ="deManual")
    private String deManual;    //是否手工录入交易
    @Column(name = "DE_MANUAL_DATE")
    @JSONField(name ="deManualDate")
    private String deManualDate;    //手工录入日期
    @Column(name = "DE_DEAL_CONFIRMATION")
    @JSONField(name ="deDealConfirmation")
    private String deDealConfirmation;    //是否成交确认过（中债登、清算所）
    @Column(name = "DE_REPO_STARTYMD")
    @JSONField(name ="deRepoStartYmd")
    private String deRepoStartYmd;    //开始日期
    @Column(name = "DE_REPO_ENDYMD")
    private String deRepoEndYmd;    //结束日期
    @Column(name = "DE_SETTLEDATE1ST")
    @JSONField(name ="deSettleDate1st")
    private String deSettleDate1st;    //首次结算日期
    @Column(name = "DE_SETTLEDATE2ND")
    @JSONField(name ="deSettleDate2nd")
    private String deSettleDate2nd;    //到期结算日期
    @Column(name = "DE_PLEDGED_INST")
    @JSONField(name ="dePledgedInst")
    private BigDecimal dePledgedInst;    //质押贷款偿付利息金額（合计值）
    @Column(name = "DE_COLLATERAL_FEE")
    @JSONField(name ="deCollateralFee")
    private BigDecimal deCollateralFee;    //借券费用金額（合计值）
    @Column(name = "DE_COLLATERAL_RATE")
    @JSONField(name ="deCollateralRate")
    private BigDecimal deCollateralRate;    //借券担保金率
    @Column(name = "DE_COLLATERAL_AMOUNT")
    @JSONField(name ="deCollateralAmount")
    private BigDecimal deCollateralAmount;    //借券担保金额
    @Column(name = "DE_TAX")
    @JSONField(name ="deTax")
    private BigDecimal deTax;    //印花税
    @Column(name = "DE_TRANSFER_FEE1")
    @JSONField(name ="deTransferFee1")
    private BigDecimal deTransferFee1;    //过户费
    @Column(name = "DE_SETTLE_FEE")
    @JSONField(name ="deSettleFee")
    private BigDecimal deSettleFee;    //结算费
    @Column(name = "DE_TRADE_FEE")
    @JSONField(name ="deTradeFee")
    private BigDecimal deTradeFee;    //交易规费
    @Column(name = "DE_TRANSFER_FEE2")
    @JSONField(name ="deTransferFee2")
    private BigDecimal deTransferFee2;    //经手费
    @Column(name = "DE_SECURITY_MANAGE_FEE")
    @JSONField(name ="deSecurityManageFee")
    private BigDecimal deSecurityManageFee;    //证管费
    @Column(name = "DE_OTHER_FEE")
    @JSONField(name ="deOtherFee")
    private BigDecimal deOtherFee;    //其他费
    @Column(name = "DE_MATCHORD_STATUS")
    @JSONField(name ="deMatchordStatus")
    private String deMatchordStatus;    //与委托单匹配状况（1：匹配成功；0：匹配不成功）
    @Column(name = "DE_CANCEL_FLG")
    @JSONField(name ="deCancelFlg")
    private String deCancelFlg;    //取消标识
    @Column(name = "DE_BROKER_FLG")
    @JSONField(name ="deBrokerFlg")
    private String deBrokerFlg;    //Broker标识
    @Column(name = "AUDIT_CREATEDBY")
    @JSONField(name ="auditCreatedBy")
    private String auditCreatedBy;    //留痕字段-创建人
    @Column(name = "AUDIT_CREATETIME")
    @JSONField(name ="auditCreateTime")
    private String auditCreateTime;    //留痕字段-创建时间
    @Column(name = "AUDIT_MODIFIEDBY")
    @JSONField(name ="auditModifiedBy")
    private String auditModifiedBy;    //留痕字段-修改人
    @Column(name = "AUDIT_MODIFIEDTIME")
    @JSONField(name ="auditModifiedTime")
    private String auditModifiedTime;    //留痕字段-修改时间
}
