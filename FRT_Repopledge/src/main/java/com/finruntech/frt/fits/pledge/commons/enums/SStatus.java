package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/6/15.
 */
public enum SStatus {

    UNCONFIRMED("未确认", "1"),
    CONFIRMED("已确认", "2"),
            ;
    private String displayName;
    private String code;

    SStatus(String displayName, String code) {
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
