package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * 结算类型
 * @author admin
 *
 */
public enum SprocType {
	DAYTIME("日间", "1"),
	DAYEND ("日终", "2");
    
    
    private String displayName;
    private String code;

    SprocType(String displayName, String code) {
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
