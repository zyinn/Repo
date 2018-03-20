package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/6/15.
 */
public enum SProcStatus {
    UNSETTLED("未结算", "1"),
    SETTLED("已结算", "2"),
    ;
    private String displayName;
    private String code;

    SProcStatus(String displayName, String code) {
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
