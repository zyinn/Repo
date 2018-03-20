package com.finruntech.frt.fits.pledge.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2017/12/28.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(FitsRepoPldgBondMgtEntity.FitsRepoPldgBondMgtEntityPK.class)
@Table(name = "FRT_FITS_REPOPLDG_BONDMGT")
public class FitsRepoPldgBondMgtEntity {
    @Column(name = "BM_DEAL_ID")
    private long bmDealId;    //自增长记录序号
    @Id
    @Column(name = "BM_PORTFOLIO_SECU")
    private String bmPortfolioSecu;        //组合证券帐号
    @Id
    @Column(name = "BM_PORTFOLIO_CASH")
    private String bmPortfolioCash;        //组合资金帐号
    @Id
    @Column(name = "BM_HOST_SECU")
    private String bmHostSecu;        //托管证券帐号
    @Id
    @Column(name = "BM_HOST_CASH")
    private String bmHostCash;        //托管资金帐号
    @Id
    @Column(name = "BM_BOND_CODE")
    private String bmBondCode;        //债券代码
    @Column(name = "BM_BOND_NAME")
    private String bmBondName;        //债券名称
    @Column(name = "BM_BOND_BS")
    private BigDecimal bmBondBs;        //自有数量(现券买卖)
    @Column(name = "BM_BOND_OUTRIGHT")
    private BigDecimal bmBondOutRight;        //买断式回购数量
    @Column(name = "BM_BOND_LEND")
    private BigDecimal bmBondLend;        //融入融出数量
    @Column(name = "BM_BOND_PLEDGE")
    private BigDecimal bmBondPledge;        //质押式回购数量
    @Column(name = "BM_BOND_AVAILABLE")
    private BigDecimal bmBondAvailAble;        //可用数量

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class FitsRepoPldgBondMgtEntityPK implements Serializable {
        private static final long serialVersionUID = -9031512642696449018L;
        @Id
        @Column(name = "BM_PORTFOLIO_SECU", nullable = false)
        private String bmPortfolioSecu;        //组合证券帐号
        @Id
        @Column(name = "BM_PORTFOLIO_CASH", nullable = false)
        private String bmPortfolioCash;        //组合资金帐号
        @Id
        @Column(name = "BM_HOST_SECU", nullable = false)
        private String bmHostSecu;        //托管证券帐号
        @Id
        @Column(name = "BM_HOST_CASH", nullable = false)
        private String bmHostCash;        //托管资金帐号
        @Id
        @Column(name = "BM_BOND_CODE", nullable = false)
        private String bmBondCode;        //债券代码
    }
}
