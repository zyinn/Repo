package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgQryDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoSettleInstDto;
import com.finruntech.frt.fits.pledge.model.dto.RepoInstDto;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by weihubin on 2018/1/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsRepoSettleServiceTest {

    @Autowired
    private FitsRepoSettleService fitsRepoSettleService;

    @Test
    public void settleInstQry() throws Exception {
        String msg = "{\"dealExeStatus\": \"1\",\"orderBy\": \"desc\", \"orderColumn\":\"deNum\",\"pageNum\":1,\"pageSize\":10, \"sStatus\":\"1\",\"execution\":\"NIB\"}";
        JSONObject jsonObject = new JSONObject();
        PageInfo<?> pageInfo = fitsRepoSettleService.settleInstQry(jsonObject);
        for(Object obj:pageInfo.getList()){
            FitsRepoSettleInstDto dto = (FitsRepoSettleInstDto)obj;
            System.out.println("*****************************************************");
            System.out.println(dto.getDeNum()+"&&"+dto.getDeRepoCode());
            System.out.println("*****************************************************");
        }
    }

    @Test
    public void settleInstComfirm() throws Exception {

        String msg = "{\"scashAmount\":8000, \"sId\":352, \"setDate\":\"2018-01-15\", \"deNum\":\"DE20180115000221\", \"deRepoCode\":\"OR0030\"}";

        fitsRepoSettleService.settleInstComfirm(msg);

    }

    @Test
    public void matureAmountModify() throws Exception {

        String msg = "{\"scashAmount\":8000, \"sId\":352, \"setDate\":\"2018-01-15\"}";

        fitsRepoSettleService.matureAmountModify(msg);

    }

    @Test
    public void nibRepoPldgQuery() throws Exception {

        String msg = "{\"portCash\":\"010101000001\"}";

        PageInfo<?> p=fitsRepoSettleService.nibRepoPldgQuery(msg);

        for(Object obj:p.getList()){
            FitsRepoPldgQryDto dto = (FitsRepoPldgQryDto)obj;
            System.out.println("*****************************************************");
            System.out.println(dto.getPcName()+"&&"+dto.getTradeDirection());
            System.out.println("*****************************************************");
        }
    }

}