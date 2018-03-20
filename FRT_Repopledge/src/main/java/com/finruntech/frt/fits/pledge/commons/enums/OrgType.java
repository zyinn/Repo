package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/6/1.
 */
public enum OrgType {
    OPPONORG("对手机构", "1"),
    PARTYORG("本方机构", "2"),
    ;
    private String displayName;
    private String code;

    OrgType(String displayName, String code) {
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
