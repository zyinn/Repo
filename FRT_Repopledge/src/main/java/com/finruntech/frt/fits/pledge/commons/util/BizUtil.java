package com.finruntech.frt.fits.pledge.commons.util;

import java.util.List;

/**
 * Created by lenovo on 2017/12/8.
 */
public class BizUtil {

    /**
     * 判断用户是否具有该投资组合的操作权限
     * @param psCashNum
     * @param portAcctCashList
     * @return
     */
    public static boolean isPsCashNumAuth(String psCashNum, List<String> portAcctCashList){
        boolean isPsCashNumAuth = false;
        if(portAcctCashList == null || portAcctCashList.size() == 0){
            return isPsCashNumAuth;
        }
        for(String str : portAcctCashList){
            if(str.equals(psCashNum)){
                isPsCashNumAuth = true;
                break;
            }
        }
        return  isPsCashNumAuth;
    }
}
