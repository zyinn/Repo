package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.model.dto.FitsPledgeInstResultDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgOrdDto;
import com.finruntech.frt.fits.pledge.model.dto.RepoInstDto;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgInstMapper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/1/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsRepoPldgExeEntrustServiceTest {

    @Autowired
    private FitsRepoPldgExeEntrustService fitsRepoPldgExeEntrustService;
    @Autowired
    private FitsRepoPldgInstMapper fitsRepoPldgInstMapper;

    @Test
    public void exeInstQuery() throws Exception {
        String msg = "\n" +
                "{\"dealExeStatus\": \"1\",\"orderBy\": \"desc\", \"orderColumn\":\"F_FORM_NUM\",\"pageNum\":1,\"pageSize\":10}";
        PageInfo<?> pageInfo = fitsRepoPldgExeEntrustService.exeInstQuery(msg);
        for(Object obj:pageInfo.getList()){
            RepoInstDto dto = (RepoInstDto)obj;
            System.out.println("*****************************************************");
            System.out.println(dto.getFInstrument()+"&&"+dto.getFFormNum());
            System.out.println("*****************************************************");
        }
    }

    @Test
    public void exeInvstOper() throws Exception {
        String msg = "{\"fExecutor\": 10221, \"userId\": 10221,\"fPortfacctCash\":\"010101000001\",\"portAcctCashList\":[\"010101000001\",\"010102000041\"],\"fFormNum\":\"247\"}";
        fitsRepoPldgExeEntrustService.exeInvstOper(msg);
    }



    @Test
    public void entrustInvstQuery() throws Exception {
        String msg = "\n" +
                "{\"userId\": \"10221\",\"orderBy\": \"desc\", \"orderColumn\":\"F_FORM_NUM\",\"pageNum\":1,\"pageSize\":10}";
        PageInfo<?> pageInfo = fitsRepoPldgExeEntrustService.entrustInvstQuery(msg);
        for(Object obj:pageInfo.getList()){
            RepoInstDto dto = (RepoInstDto)obj;
            System.out.println("*****************************************************");
            System.out.println(dto.getFInstrument()+"&&"+dto.getFFormNum());
            System.out.println("*****************************************************");
        }
    }

    @Test
    public void qryOrderlist() throws Exception {
        String msg = "{\"orderBy\": \"desc\", \"orderColumn\":\"ordNum\",\"pageNum\":1,\"pageSize\":10,\"fFormNum\":\"253\"}";
        PageInfo<?> pageInfo =  fitsRepoPldgExeEntrustService.qryOrderlist(msg);
        for(Object obj:pageInfo.getList()){
            FitsRepoPldgOrdDto dto = (FitsRepoPldgOrdDto)obj;
            System.out.println("*****************************************************");
            System.out.println(dto.getOrdNum()+"&&"+dto.getOrdId()+"&&"+dto.getOrdSetDate1st());
            System.out.println("*****************************************************");
        }
    }

    @Test
    public void addOrderInitInfo() throws Exception {
        String msg = "{\"fTrdParValue\": 200000, \"fPledgeRatio\":0.86}";
        Map rtnMap = fitsRepoPldgExeEntrustService.addOrderInitInfo(msg);
        System.out.println("==> orderPrint rtnMap:" + rtnMap);
    }


    @Test
    public void dealRepoPldgOrderOperCancel() throws Exception {
        fitsRepoPldgExeEntrustService.dealRepoPldgOrderOper("cancelOrder", cacelOrderOperParam());
    }


    @Test
    public void dealRepoPldgOrderOper() throws Exception {
        fitsRepoPldgExeEntrustService.dealRepoPldgOrderOper("addOrder", addOrderOperParam());
    }

    private String addOrderOperParam() {
        FitsPledgeInstResultDto fitsPledgeInstResultDto =
                (FitsPledgeInstResultDto) fitsRepoPldgInstMapper.qryInstByFormNum("253"); //查询指令的信息
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(fitsPledgeInstResultDto));
        jsonObject.put("fExecutor", "10221");
        jsonObject.put("userId", "10221");
        jsonObject.put("accCusSecu", JSONObject.parse("{\"hsAccNumber\": \"23232332\"}"));
        jsonObject.put("accCusCash", JSONObject.parse("{\"hcAccNumber\": \"18627367366\"}"));
        return jsonObject.toJSONString();
    }


    private String cacelOrderOperParam() {
         return "{\"ordNum\":\"ORD20171226001244\",\"ordStatus\":\"1\",\"userId\":\"10221\",\"fExecutor\":\"10221\"}";
    }


    @Test
    public void orderPrint() throws Exception {
        String msg = "{\"ordNum\": \"REPO111\"}";
        Map rtnMap = fitsRepoPldgExeEntrustService.orderPrint(msg);
        System.out.println("==> orderPrint rtnMap:" + rtnMap);
    }

}