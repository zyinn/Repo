package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.model.FitsAccPortfolioSecuEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinan.zhang on 2018/1/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsAccPortfolioSecuServiceTest {
    @Autowired
    private FitsAccPortfolioSecuService fitsAccPortfolioSecu;
    @Test
    public void findFirstByPsAcctClassAndPsCashNum(){
        Map<String,String> map = new HashMap<>();
        map.put("psAcctClass","4");
        map.put("psCashNum","010101000001");
        String psAcctClass="4";
        String psCashNum="010101000001";
        FitsAccPortfolioSecuEntity entity = fitsAccPortfolioSecu.findFirstByPsAcctClassAndPsCashNum(psAcctClass, psCashNum);
        Assert.assertTrue("投资组合查询失败",entity!=null);
    }
}
