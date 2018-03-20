package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/6/1.
 * 委托单状态
 */
public enum OrdStatus {
    ORDERED("委托中", "1"),
    ALLTRAN("全部成交", "0"),
    ;
    private String displayName;
    private String code;

    OrdStatus(String displayName, String code) {
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
