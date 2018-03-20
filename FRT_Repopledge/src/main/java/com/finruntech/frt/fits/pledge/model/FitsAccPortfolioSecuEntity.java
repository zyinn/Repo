package com.finruntech.frt.fits.pledge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yinan.zhang on 2018/1/2.
 */
@Entity
@Table(name="FRT_FITS_ACC_PORTFOLIO_SECU")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsAccPortfolioSecuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ")
    @SequenceGenerator(name="SEQ",sequenceName="ACC_PORTFOLIO_SECU_SEQ", allocationSize = 1)
    @Column(name = "PS_ID")
    private long psId;
    @Column(name = "PS_ENT_ID")
    private long psEntId;
    @Column(name = "PS_NAME")
    private String psName;
    @Column(name = "PS_ACCT_CLASS")
    private String psAcctClass;
    @Column(name = "PS_ACCT_NUM")
    private String psAcctNum;
    @Column(name = "PS_CASH_NUM")
    private String psCashNum;
    @Column(name = "PS_RESOURCE_NUM")
    private String psResourceNum;
    @Column(name = "PS_DEPT")
    private String psDept;
    @Column(name = "PS_BIZ_STREAM")
    private String psBizStream;
    @Column(name = "PS_ASSET_CLASS")
    private String psAssetClass;
    @Column(name = "PS_FINANCE_CLASS")
    private String psFinanceClass;
    @Column(name = "PS_STATUS")
    private String psStatus;
}
