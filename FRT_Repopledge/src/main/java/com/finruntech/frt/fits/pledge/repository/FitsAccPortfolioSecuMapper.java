package com.finruntech.frt.fits.pledge.repository;

import com.finruntech.frt.fits.pledge.model.FitsAccPortfolioSecuEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by yinan.zhang on 2018/1/2.
 */
@Repository("fitsAccPortfolioSecuMapper")
public interface FitsAccPortfolioSecuMapper {
    FitsAccPortfolioSecuEntity findAccPSecuEnityByAcctClassAndCashNum(Map<String,String> map);

    FitsAccPortfolioSecuEntity findAccPSecuEnityByCashNumAndSecuNum(Map<String,String> map);
}
