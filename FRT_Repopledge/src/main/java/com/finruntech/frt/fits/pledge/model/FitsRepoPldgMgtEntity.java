package com.finruntech.frt.fits.pledge.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.finruntech.frt.fits.pledge.commons.annotation.ValidDbLength;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/10.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FRT_FITS_REPOPLDG_PLDGMGT")
@IdClass(FitsRepoPldgMgtEntity.FitsRepoPldgMgtEntityPK.class)
public class FitsRepoPldgMgtEntity {
    @Id
    @Column(name = "SUBJECT_NUM")
    @JSONField(name = "subJectNum")
    private String subJectNum;//指令编号/委托编号/成交编号
    @Id
    @Column(name = "SUBJECT_TYPE")
    @JSONField(name = "subJectType")
    private String subJectType;//指令编号/委托编号/成交编号
    @Id
    @Column(name = "P_REPO_CODE")
    @JSONField(name = "pRepoCode")
    private String pRepoCode;//质押式回购代码
    @Id
    @Column(name = "P_BOND_CODE")
    @JSONField(name = "pBondCode")
    @ValidDbLength(message = "质押债券代码最大值为30字符!", length = 30)
    private String pBondCode;//质押债券代码
    @Column(name = "P_REPO_NAME")
    @JSONField(name = "pRepoName")
    private String pRepoName;//质押式回购名称
    @Column(name = "P_BOND_NAME")
    @JSONField(name = "pBondName")
    @ValidDbLength(message = "质押债券名称最大值为60字符!", length = 60)
    private String pBondName;//质押债券名称
    @Column(name = "P_ACCRINTST")
    @JSONField(name = "pAccrIntst")
    private BigDecimal pAccrIntst;//质押券的应计利息
    @Column(name = "P_UDLYPRCE_CLEAN")
    @JSONField(name = "pUdlyprceClean")
    private BigDecimal pUdlyprceClean;//质押券券的净价
    @Column(name = "P_HAIRCUT")
    @JSONField(name = "pHairCut")
    private BigDecimal pHairCut;//质押券折扣率
    @Column(name = "P_DISCOUNTRATIO")
    @JSONField(name = "pDiscountRatio")
    private BigDecimal pDiscountRatio;//质押券抵押比例
    @Column(name = "P_COUNT")
    @JSONField(name = "pCount")
    private BigDecimal pCount;//质押券数量
    @Column(name = "P_SETLAMOUNT")
    @JSONField(name = "pSetlAmount")
    private BigDecimal pSetlAmount;//质押券结算金额
    @Column(name = "P_REPO_DIRECTION")
    @JSONField(name = "pRepoDirection")
    private String pRepoDirection;//回购方向(正回购/逆回购)


    // TODO: 2018/1/26 以下为新添加字段
    @Column(name = "P_PORTFOLIO_SECU")
    @JSONField(name = "pPortfolioSecu")
    private String pPortfolioSecu;  //组合证券帐号
    @Column(name = "P_PORTFOLIO_CASH")
    @JSONField(name = "pPortfolioCash")
    private String pPortfolioCash;//组合资金帐号
    @Column(name = "P_HOST_SECU")
    @JSONField(name = "pHostSecu")
    private String pHostSecu;//托管证券帐号
    @Column(name = "P_HOST_CASH")
    @JSONField(name = "pHostCash")
    private String pHostCash;//托管资金帐号
    @Column(name = "P_DEAL_DATE")
    @JSONField(name = "pDealDate")
    private String pDealDate;//成交日期 （若是成交记录DEAL类型时）
    @Column(name = "P_SETTLEDATE1ST")
    @JSONField(name = "pSettleDate1st")
    private String pSettleDate1st;//首次结算日期
    @Column(name = "P_SETTLEDATE2ND")
    @JSONField(name = "pSettleDate2nd")
    private String pSettleDate2nd;//到期结算日期

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class FitsRepoPldgMgtEntityPK implements Serializable {
        private static final long serialVersionUID = -9031512642696449018L;
        @Id
        @Column(name = "SUBJECT_NUM")
        @JSONField(name = "subJectNum")
        private String subJectNum;//指令编号/委托编号/成交编号
        @Id
        @Column(name = "SUBJECT_TYPE")
        @JSONField(name = "subJectType")
        private String subJectType;//指令编号/委托编号/成交编号
        @Id
        @Column(name = "P_REPO_CODE")
        @JSONField(name = "pRepoCode")
        private String pRepoCode;//质押式回购代码
        @Id
        @Column(name = "P_BOND_CODE")
        @JSONField(name = "pBondCode")
        private String pBondCode;//质押债券代码
    }
}
