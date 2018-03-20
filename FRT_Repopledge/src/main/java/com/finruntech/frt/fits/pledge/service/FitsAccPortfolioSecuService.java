package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.model.FitsAccPortfolioSecuEntity;
import com.finruntech.frt.fits.pledge.repository.FitsAccPortfolioSecuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 投资组合查询
 * Created by yinan.zhang on 2018/1/2.
 */
@Service
public class FitsAccPortfolioSecuService {
    @Autowired
    private FitsAccPortfolioSecuMapper fitsAccPortfolioSecuMapper;

    /**
     * 根据账户类型、对应组合资金账户查询投资组合
     * @param psAcctClass 账户类型
     * @param psCashNum 对应组合资金账户
     * @return FitsAccPortfolioSecuEntity
     */
    public FitsAccPortfolioSecuEntity findFirstByPsAcctClassAndPsCashNum(String psAcctClass,String psCashNum){
        Map<String,String> map = new HashMap<>();
        map.put("psAcctClass",psAcctClass);
        map.put("psCashNum",psCashNum);
        return fitsAccPortfolioSecuMapper.findAccPSecuEnityByAcctClassAndCashNum(map);
    }

    /**
     * 根据组合证券账户、对应组合资金账户查询投资组合
     * @param psAcctNum 组合证券账户
     * @param psCashNum 对应组合资金账户
     * @return
     */
    public FitsAccPortfolioSecuEntity findFirstByPsAcctNumAndPsCashNum(String psAcctNum,String psCashNum){
        Map<String,String> map = new HashMap<>();
        map.put("psAcctNum",psAcctNum);
        map.put("psCashNum",psCashNum);
        return fitsAccPortfolioSecuMapper.findAccPSecuEnityByCashNumAndSecuNum(map);
    }
}
