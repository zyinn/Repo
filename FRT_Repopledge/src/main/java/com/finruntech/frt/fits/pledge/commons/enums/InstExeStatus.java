package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2018/1/11.
 */
public enum  InstExeStatus {
    Inst_notExed("未执行","1"),
    Inst_Exed("已执行","2");

    private String displayName;
    private String code;

    InstExeStatus(String name, String code) {
        this.displayName = name;
        this.code = code;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getCode() {
        return code;
    }
}
