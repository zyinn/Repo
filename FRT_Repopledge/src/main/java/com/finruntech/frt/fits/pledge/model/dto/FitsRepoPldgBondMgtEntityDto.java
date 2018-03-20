package com.finruntech.frt.fits.pledge.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/3.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoPldgBondMgtEntityDto{

    private long bmDealId;    //自增长记录序号

    private String bmPortfolioSecu;        //组合证券帐号

    private String bmPortfolioCash;        //组合资金帐号

    private String bmHostSecu;        //托管证券帐号

    private String bmHostCash;        //托管资金帐号

    private String bmBondCode;        //债券代码

    private String bmBondName;        //债券名称

    private BigDecimal bmBondBs;        //自有数量(现券买卖)

    private BigDecimal bmBondOutRight;        //买断式回购数量

    private BigDecimal bmBondLend;        //融入融出数量

    private BigDecimal bmBondPledge;        //质押式回购数量

    private BigDecimal bmBondAvailAble;        //可用数量

    private String psName;//组合证券帐号名称

    private BigDecimal bPriceDirty; //全价

    private BigDecimal bYield; //收益率

    private BigDecimal bIssueParvalue;//单张面额

    private String archiveDate;
    private boolean submitFlag; //标识符

    private String psAcctClassName;//投资组合会计分类名称

}
