package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/12/13.
 */
public enum MatchOrdStatus {
    match_succ("匹配成功", "1"),
    match_fail("匹配不成功", "0"),
    ;
    private String displayName;
    private String code;

    MatchOrdStatus(String displayName, String code) {
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
