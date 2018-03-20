package com.finruntech.frt.fits.pledge.service;


import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgDealDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgOrdDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgQueryInstDto;
import com.github.pagehelper.PageInfo;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lenovo on 2018/1/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsRepoDealServiceTest {

    @Autowired
    private FitsRepoDealService fitsRepoDealService;


    @Test
    public void matchDealList() throws Exception {
        String msg = "";
        PageInfo<?>  pageInfo= fitsRepoDealService.matchDealList(msg);
        for(Object obj:pageInfo.getList()){
            FitsRepoPldgDealDto dto = (FitsRepoPldgDealDto)obj;
            System.out.println("*****************************************************");
            System.out.println(dto.getDeDealId()+"&&"+dto.getDeDealDate()+"&&"+dto.getDeExeIdNum());
            System.out.println("*****************************************************");
        }
    }

    @Test
    public void matchOrderList() throws Exception {
        String msg = "";
        PageInfo<?>  pageInfo= fitsRepoDealService.matchOrderList(msg);
        for(Object obj:pageInfo.getList()){
            FitsRepoPldgOrdDto dto = (FitsRepoPldgOrdDto)obj;
            System.out.println("*****************************************************");
            System.out.println(dto.getOrdId()+"&&"+dto.getOrdCount());
            System.out.println("*****************************************************");
        }
    }


    @Test
    public void tradeEntryAubmit() throws Exception {
        String msg = "{\"test\":\"123\"}";
        fitsRepoDealService.tradeEntryAubmit(msg);
    }

    @Test
    public void repopldgMatchTrans() throws Exception {


    }



    @Test
    public void matchSettleRepopldgDeal() throws Exception {


    }

    @Test
    public void findRepoDealServiceTest() {
        FitsRepoPldgQueryInstDto dto = new FitsRepoPldgQueryInstDto();
        dto.setOrderBy("desc");
        dto.setOrderColumn("deDealId");
        dto.setPageNum(0);
        dto.setPageSize(10);
        dto.setEndDate("2018-01-10");
        dto.setStartDate("2018-01-10");
        dto.setFPortfAcctSecu("010101000001");
        PageInfo pageInfo = fitsRepoDealService.findRepoDealService(dto);
        Assume.assumeTrue("成交查询失败！", pageInfo.getList() != null);
    }
}
