package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.model.dto.FitsRepoAttrDto;
import com.github.pagehelper.PageInfo;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yinan.zhang on 2018/1/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsRepoAttrServiceTest {
    @Autowired
    private FitsRepoAttrService fitsRepoAttrService;
    @Test
    public void fitsRepoAttrServiceTest(){
        FitsRepoAttrDto dto = new FitsRepoAttrDto();
        dto.setRaMarket("NIB");
        dto.setRaRepoCode("%O%");
        dto.setRaRepoType("");
        PageInfo<?> pageInfo = fitsRepoAttrService.queryFitsRepoAttrEntityList(dto);
        Assume.assumeTrue("查询回购品种失败！",pageInfo.getList()!=null);
    }
}
