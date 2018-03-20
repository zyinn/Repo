package com.finruntech.frt.fits.pledge.mapper;

import com.finruntech.frt.fits.pledge.RepoApplication;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgOrdEntity;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgOrdMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RepoApplication.class)
public class FitsRepoPldgOrdMapperTest {

    @Autowired
    private FitsRepoPldgOrdMapper fitsRepoPldgOrdMapper;

    @Test
    public void insertRepoPldgOrdTest(){
        FitsRepoPldgOrdEntity fitsRepoPldgOrdEntity = new FitsRepoPldgOrdEntity();
        fitsRepoPldgOrdEntity.setOrdId(new BigDecimal("666"));
        fitsRepoPldgOrdEntity.setOrdNum("REPO112");
        fitsRepoPldgOrdEntity.setOrdInvinstNum("REPOINST1112");
        fitsRepoPldgOrdMapper.insertRepoPldgOrd(fitsRepoPldgOrdEntity);

    }

    @Test
    public void findRepoPldgOrdByInstNumTest(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("instNum", "REPOINST1111");
        List<FitsRepoPldgOrdEntity> list =  fitsRepoPldgOrdMapper.findRepoPldgOrd(paramMap);
        for(FitsRepoPldgOrdEntity fitsRepoPldgOrdEntity:list){
            System.out.println(" ord id:"  + fitsRepoPldgOrdEntity.getOrdId() + ",");
        }

    }

}
