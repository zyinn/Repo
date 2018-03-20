package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * 指令类型
 * @author admin
 *
 */
public enum InstType {
	TRADE_INST("交易指令", "1"),
	NOTRADE_INST ("非交易指令", "2");
    
    
    private String displayName;
    private String code;

    InstType(String displayName, String code) {
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
