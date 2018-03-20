package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * 证券结算方向
 */
public enum SsecuDirection {


	SSECU_BUY("买入", "1"),
	SSECU_SOLD ("卖出", "2"),
	SSECU_FREEZE ("冻结", "3"),
	SSECU_THAWED ("解冻", "4"),
	SSECU_INTO ("融入", "5"),
	SSECU_THAWOUT ("融出", "6");
    
    private String displayName;
    private String code;

    SsecuDirection(String displayName, String code) {
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
