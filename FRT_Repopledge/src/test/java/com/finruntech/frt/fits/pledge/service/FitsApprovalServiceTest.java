package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.model.dto.FitsRepoApprovalDto;
import com.finruntech.frt.fits.pledge.model.dto.approval.ProcessParamDto;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Zhangws on 2018/1/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsApprovalServiceTest {
    @Autowired
    private FitsRepoApprovalService fitsApprovalService;

    @Test
    public void repoStartProcess(){
        ProcessParamDto ppDto =new ProcessParamDto();
        ppDto.setFormId("008");
        ppDto.setKey("bRepoProcess");
        ppDto.setUserId("10002");
        ppDto.setVariables("{\"bMoney\":200000001}");
        fitsApprovalService.repoStartProcess(ppDto);
//        int i=fitsApprovalService.repoStartProcess(ppDto);
//        Assert.assertTrue("提价审批失败",i!=0);
    }

    @Test
    public void repoApprovalQry(){
        FitsRepoApprovalDto dto=new FitsRepoApprovalDto();
        dto.setStartDate("2018-01-08 00:00:00");
        dto.setEndDate("2018-01-08 23:59:59");
        dto.setUserId("10008");
//        dto.setOverUserId("10008");
//        dto.setUserName("FIHead");
        dto.setFStatus("2,3");
        dto.setOrderBy("desc");
        dto.setOrderColumn("aprvformNum");
        dto.setPageNum(1);
        dto.setPageSize(10);
        PageInfo<?> s=fitsApprovalService.repoApprovalQry(dto);
        Assume.assumeTrue("查询回购审批失败！",s.getList()!=null);
    }

    @Test
    public void repoApproval(){
        ProcessParamDto d =new ProcessParamDto();
        d.setUserId("10052");
        d.setFlag("1");
        d.setOption("通过");
        d.setProcessId("192729");
        d.setFormId("008");

//        ProcessParamDto d =new ProcessParamDto();
//        d.setUserId("10052");
//        d.setFlag("0");
//        d.setOption("驳回");
//        d.setProcessId("192729");
//        d.setFormId("008");
        int i=fitsApprovalService.repoApproval(d);
        Assert.assertTrue("指令审批失败",i!=0);
    }

    @Test
    public void repoRestartProcess(){
        ProcessParamDto d =new ProcessParamDto();
        d.setUserId("10002");
        d.setProcessId("192687");
        d.setFormId("008");
        d.setVariables("{\"bMoney\":200000001}");
        int i=fitsApprovalService.repoRestartProcess(d);
        Assert.assertTrue("指令审批失败",i!=0);
    }

    @Test
    public void repoCancelProcess(){
        ProcessParamDto d =new ProcessParamDto();
        d.setUserId("10002");
        d.setFlag("3");
        d.setOption("撤销");
        d.setProcessId("192729");
        d.setFormId("008");
        int i=fitsApprovalService.repoCancelProcess(d);
        Assert.assertTrue("指令审批失败",i!=0);
    }
}
