package com.finruntech.frt.fits.pledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lenovo on 2017/12/7.
 */
@Entity
@Table(name = "FRT_FITS_SYSTEMSTATUS")
public class FrtFitsSystemStatusEntity implements Serializable {

    private static final long serialVersionUID = -1704179320479611039L;

    @Id
    @Column(name = "MARKET_TYPE")
    private String marketType; //银行间市场、交易所市场等
    @Column(name = "LAST_BIZDATE")
    private String lastBizdate; //上一工作日
    @Column(name = "CURR_BIZDATE")
    private String currBizdate; //当前工作日
    @Column(name = "NEXT_BIZDATE")
    private String nextBizdate; //下一工作日
    @Column(name = "EOD_BATCH_STATUS")
    private String eodBatchStatus; //EOD跑批状态
    @Column(name = "MOR_BATCH_STATUS")
    private String morBatchStatus; //MORNING跑批状态

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getLastBizdate() {
        return lastBizdate;
    }

    public void setLastBizdate(String lastBizdate) {
        this.lastBizdate = lastBizdate;
    }

    public String getCurrBizdate() {
        return currBizdate;
    }

    public void setCurrBizdate(String currBizdate) {
        this.currBizdate = currBizdate;
    }

    public String getNextBizdate() {
        return nextBizdate;
    }

    public void setNextBizdate(String nextBizdate) {
        this.nextBizdate = nextBizdate;
    }

    public String getEodBatchStatus() {
        return eodBatchStatus;
    }

    public void setEodBatchStatus(String eodBatchStatus) {
        this.eodBatchStatus = eodBatchStatus;
    }

    public String getMorBatchStatus() {
        return morBatchStatus;
    }

    public void setMorBatchStatus(String morBatchStatus) {
        this.morBatchStatus = morBatchStatus;
    }
}
