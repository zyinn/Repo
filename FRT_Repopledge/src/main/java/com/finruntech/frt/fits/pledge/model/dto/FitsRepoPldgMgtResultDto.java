package com.finruntech.frt.fits.pledge.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/15.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoPldgMgtResultDto {

    @JSONField(name = "subJectNum")
    private String subJectNum;//指令编号/委托编号/成交编号

    @JSONField(name = "subJectType")
    private String subJectType;//指令编号/委托编号/成交编号

    @JSONField(name = "pRepoCode")
    private String pRepoCode;//质押式回购代码

    @JSONField(name = "pBondCode")
    private String pBondCode;//质押债券代码

    @JSONField(name = "pRepoName")
    private String pRepoName;//质押式回购名称

    @JSONField(name = "pBondName")
    private String pBondName;//质押债券名称

    @JSONField(name = "pAccrIntst")
    private BigDecimal pAccrIntst;//质押券的应计利息

    @JSONField(name = "pUdlyprceClean")
    private BigDecimal pUdlyprceClean;//质押券券的净价

    @JSONField(name = "pHairCut")
    private BigDecimal pHairCut;//质押券折扣率

    @JSONField(name = "pDiscountRatio")
    private BigDecimal pDiscountRatio;//质押券抵押比例

    @JSONField(name = "pCount")
    private BigDecimal pCount;//质押券数量

    @JSONField(name = "pSetlAmount")
    private BigDecimal pSetlAmount;//质押券结算金额
    @JSONField(name = "bPriceDirty")
    private BigDecimal bPriceDirty;//全价
    @JSONField(name = "bIssueParvalue")
    private BigDecimal bIssueParvalue;//单张票面金额
    @JSONField(name = "psAcctClassName")
    private String psAcctClassName;//投资组合会计分类
    @JSONField(name = "pPortfolioSecu")
    private String pPortfolioSecu;//组合证券帐号
    @JSONField(name = "pPortfolioCash")
    private String pPortfolioCash;//组合资金帐号
    @JSONField(name = "pHostSecu")
    private String pHostSecu;//托管证券帐号
    @JSONField(name = "pHostCash")
    private String pHostCash;//托管资金帐号

}
