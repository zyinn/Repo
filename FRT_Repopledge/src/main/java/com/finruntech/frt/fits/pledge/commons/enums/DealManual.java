package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/6/1.
 * 是否手工录入交易
 */
public enum DealManual {
    AUTOPROCESS("自动处理", "0"),
    MANUAL("人工录入", "1"),
    ;
    private String displayName;
    private String code;

    DealManual(String displayName, String code) {
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
