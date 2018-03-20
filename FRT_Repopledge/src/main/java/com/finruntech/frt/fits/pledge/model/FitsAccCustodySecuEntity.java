package com.finruntech.frt.fits.pledge.model;

import javax.persistence.*;

/**
 * 证券账户
 * Created by lenovo on 2017/5/24.
 */
@Entity
@Table(name = "FRT_FITS_ACC_CUSTODY_SECU")
public class FitsAccCustodySecuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ")
    @SequenceGenerator(name="SEQ",sequenceName="ACC_CUSTODY_SECU_SEQ", allocationSize = 1)
    @Column(name = "HS_ID")
    private Long hsId;
    @Column(name = "HS_ENT_ID")
    private Long hsEntId;
    @Column(name = "HS_ACC_NUMBER")
    private String hsAccNumber;
    @Column(name = "HS_ACC_NAME")
    private String hsAccName;
    @Column(name = "HS_CLEARING_HOUSE")
    private String hsClearingHouse;

    public FitsAccCustodySecuEntity() {
    }

    public FitsAccCustodySecuEntity(Long hsEntId, String hsAccNumber, String hsAccName, String hsClearingHouse) {
        this.hsEntId = hsEntId;
        this.hsAccNumber = hsAccNumber;
        this.hsAccName = hsAccName;
        this.hsClearingHouse = hsClearingHouse;
    }

    public Long getHsId() {
        return hsId;
    }

    public void setHsId(Long hsId) {
        this.hsId = hsId;
    }

    public Long getHsEntId() {
        return hsEntId;
    }

    public void setHsEntId(Long hsEntId) {
        this.hsEntId = hsEntId;
    }

    public String getHsAccNumber() {
        return hsAccNumber;
    }

    public void setHsAccNumber(String hsAccNumber) {
        this.hsAccNumber = hsAccNumber;
    }

    public String getHsAccName() {
        return hsAccName;
    }

    public void setHsAccName(String hsAccName) {
        this.hsAccName = hsAccName;
    }

    public String getHsClearingHouse() {
        return hsClearingHouse;
    }

    public void setHsClearingHouse(String hsClearingHouse) {
        this.hsClearingHouse = hsClearingHouse;
    }
}
