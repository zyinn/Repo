package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * 资金结算方向
 * @author admin
 *
 */
public enum ScashDirection {

	SCASH_ENTER("进", "1"),
	SCASH_OUT ("出", "2"),
	SCASH_FREEZE ("冻结", "3"),
	SCASH_THAWED ("解冻", "4");
    
    
    private String displayName;
    private String code;

    ScashDirection(String displayName, String code) {
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
