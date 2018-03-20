package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgInstEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgBondMgtDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgBondMgtEntityDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgQueryInstDto;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 指令单Test
 * Created by yinan.zhang on 2017/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsPledgeInstServiceTest {
    @Autowired
    private FitsRepoPldgInstService repoPldgInstService;
    @Autowired
    private FitsRepoPldgBondMgtService repoPldgBondMgtService;
    @Test
    public void saveFitsRepoPldgInstTest(){
        FitsRepoPldgInstEntity entity = new FitsRepoPldgInstEntity();
        entity.setAprvFormNum("112");
        entity.setFFormId(123);
        entity.setFFormNum("123");
        List<FitsRepoPldgMgtEntity> pldgMgtList = new ArrayList<>();
        FitsRepoPldgQueryInstDto dto = new  FitsRepoPldgQueryInstDto();
        int i = repoPldgInstService.savePledgeInst(entity,dto,pldgMgtList);
        Assert.assertTrue("指令保存失败",i!=0);
    }
    @Test
    public void updateFitsRepoPldgInstTest(){
        FitsRepoPldgInstEntity entity = new FitsRepoPldgInstEntity();
        entity.setAprvFormNum("112");
        entity.setFFormId(123);
        entity.setFFormNum("123");
        List<FitsRepoPldgMgtEntity> list= new ArrayList<>();
        FitsRepoPldgMgtEntity mgtEntity = new FitsRepoPldgMgtEntity();
        mgtEntity.setSubJectType("INST");
        mgtEntity.setSubJectNum(entity.getFFormNum());
        mgtEntity.setPRepoCode("OR007");
        list.add(mgtEntity);
        int i = repoPldgInstService.updateFitsPledgeInst(entity,list);
        Assert.assertTrue("修改指令失败",i!=0);
    }

    @Test
    public void queryRepoPldgBondMgtTest(){
        FitsRepoPldgBondMgtDto dto = new FitsRepoPldgBondMgtDto();
        dto.setBmPortfolioCash("010101000001");
        dto.setBmPortfolioSecu("010101000001_4");
        dto.setOrderBy("desc");
        dto.setOrderColumn("bmDealId");
        dto.setPageNum(1);
        dto.setPageSize(10);
        PageInfo<?>  s = repoPldgBondMgtService.queryFitsRepoPldgBondMgt(dto);
        Assume.assumeTrue("查询回购债券失败！",s.getList()!=null);
    }

    @Test
    public void findFitsRepoPldgInstTest(){
        FitsRepoPldgQueryInstDto dto = new FitsRepoPldgQueryInstDto();
        dto.setFPortfAcctSecu("");
        dto.setStartDate("2018-01-04");
        dto.setEndDate("2018-01-04");
        dto.setOrderBy("desc");
        dto.setOrderColumn("fFormNum");
        dto.setPageNum(1);
        dto.setPageSize(10);
        PageInfo pageInfo = repoPldgInstService.queryPledgeInst(dto);
        Assert.assertTrue("查询指令失败",pageInfo.getList()!=null);
    }
}