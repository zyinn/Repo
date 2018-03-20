package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/6/1.
 */
public enum MarketType {
    INTERBANK_MARKET("银行间市场", "1"),
    EXCHANGE_MARKET ("交易所市场", "2"),
    COUNTER_MARKET("柜台市场", "3"),
    ;
    private String displayName;
    private String code;

    MarketType(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public String getDisplayName() {
        return displayName;
    }
}
