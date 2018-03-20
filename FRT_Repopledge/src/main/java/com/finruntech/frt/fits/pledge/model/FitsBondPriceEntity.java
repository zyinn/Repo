package com.finruntech.frt.fits.pledge.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/4.
 */
@Entity
@Setter
@Getter
@Table(name = "FRT_FITS_BONDPRICE")
@IdClass(FitsBondPriceEntity.FitsBondPriceEntityPK.class)
public class FitsBondPriceEntity {
    @Id
    @Column(name = "B_ID", nullable = false)
    private String bId;// 债券ID
    @Id
    @Column(name = "B_DATE", nullable = false)
    private String bDate;// 行情日期
    @Id
    @Column(name = "B_CODE", nullable = false)
    private String bCode;// 债券代码
    @Column(name = "B_PRICE_TYPE")
    private String bPriceType;// 行情来源(类别) 如上清所，中债登等，李向改为可为NULL
    @Column(name = "B_PRICE_CLEAN")
    private BigDecimal bPriceClean;// 行情价格净价
    @Column(name = "B_PRICE_DIRTY")
    private BigDecimal bPriceDirty;//行情价格全价
    @Column(name = "B_YIELD")
    private BigDecimal bYield;// 估值收益率(中债)
    @Column(name = "B_MDURATION")
    private BigDecimal bMduration;// (中债)修正久期

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class FitsBondPriceEntityPK implements Serializable {
        private static final long serialVersionUID = -9031512642696449018L;
        @Id
        @Column(name = "B_ID", nullable = false)
        private String bId;// 债券ID
        @Id
        @Column(name = "B_DATE", nullable = false)
        private String bDate;// 行情日期
        @Id
        @Column(name = "B_CODE", nullable = false)
        private String bCode;// 债券代码
    }
}
