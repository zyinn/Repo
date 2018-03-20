package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2018/1/11.
 */
public enum RepoDirection {
    Repo_BUY("正回购", "B"),
    Repo_SELL("逆回购", "S");

    private String displayName;
    private String code;

    RepoDirection(String displayName, String code) {
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
