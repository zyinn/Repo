package com.finruntech.frt.fits.pledge.model;

import javax.persistence.*;

/**
 * Created by lenovo on 2017/5/18.
 * 资金账户
 */
@Entity
@Table(name = "FRT_FITS_ACC_CUSTODY_CASH")
public class FitsAccCustodyCashEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ")
    @SequenceGenerator(name="SEQ",sequenceName="ACC_CUSTODY_CASH_SEQ", allocationSize = 1)
    @Column(name = "HC_ID")
    private Long hcId;//ID主键
    @Column(name = "HC_ACC_NUMBER")
    private String hcAccNumber;//开户账号
    @Column(name = "HC_ACC_NAME")
    private String hcAccName;//开户户名
    @Column(name = "HC_ACC_BANK_NAME")
    private String hcAccBankName;//开户行名称
    @Column(name = "HC_ACC_BANK_LARGE")
    private String hcAccBankLarge;//开户行大额行号
    @Column(name = "HC_ENT_ID")
    private Long hcEntId;// 所属组织机构

    public Long getHcId() {
        return hcId;
    }

    public void setHcId(Long hcId) {
        this.hcId = hcId;
    }

    public String getHcAccNumber() {
        return hcAccNumber;
    }

    public void setHcAccNumber(String hcAccNumber) {
        this.hcAccNumber = hcAccNumber;
    }

    public String getHcAccName() {
        return hcAccName;
    }

    public void setHcAccName(String hcAccName) {
        this.hcAccName = hcAccName;
    }

    public String getHcAccBankName() {
        return hcAccBankName;
    }

    public void setHcAccBankName(String hcAccBankName) {
        this.hcAccBankName = hcAccBankName;
    }

    public String getHcAccBankLarge() {
        return hcAccBankLarge;
    }

    public void setHcAccBankLarge(String hcAccBankLarge) {
        this.hcAccBankLarge = hcAccBankLarge;
    }

    public Long getHcEntId() {
        return hcEntId;
    }

    public void setHcEntId(Long hcEntId) {
        this.hcEntId = hcEntId;
    }
    public FitsAccCustodyCashEntity() {
    }

    public FitsAccCustodyCashEntity(Long hcId, String hcAccNumber, String hcAccName, String hcAccBankName,
                                    String hcAccBankLarge, Long hcEntId) {
        this.hcId = hcId;
        this.hcAccNumber = hcAccNumber;
        this.hcAccName = hcAccName;
        this.hcAccBankName = hcAccBankName;
        this.hcAccBankLarge = hcAccBankLarge;
        this.hcEntId = hcEntId;
    }
    @Override
    public String toString() {
        return "FitsAccCustodyCashEntity{" +
                "hcId=" + hcId +
                ", hcAccNumber='" + hcAccNumber + '\'' +
                ", hcAccName='" + hcAccName + '\'' +
                ", hcAccBankName='" + hcAccBankName + '\'' +
                ", hcAccBankLarge='" + hcAccBankLarge + '\'' +
                ", hcEntId=" + hcEntId +
                '}';
    }
}
