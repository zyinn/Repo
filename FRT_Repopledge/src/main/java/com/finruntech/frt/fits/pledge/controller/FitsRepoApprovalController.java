package com.finruntech.frt.fits.pledge.controller;

import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoApprovalDto;
import com.finruntech.frt.fits.pledge.model.dto.approval.ProcessParamDto;
import com.finruntech.frt.fits.pledge.service.FitsRepoApprovalService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zhangws on 2018/1/3.
 */
@RestController
@CrossOrigin
public class FitsRepoApprovalController {
    @Autowired
    private FitsRepoApprovalService fitsRepoApprovalService;

    /**
     * 质押式回购-提交审批
     * @param processParamDto
     * @return
     */
    @RequestMapping(value = Constants.REPO_START_PROCESS)
    public void repoStartProcess(@RequestBody ProcessParamDto processParamDto) {
        fitsRepoApprovalService.repoStartProcess(processParamDto);
    }

    /**
     * 质押式回购-审批查询
     * @param entity
     * @return
     */
    @RequestMapping(value = Constants.REPO_APPROVAL_QRY)
    public ResponseEntity<?> repoApprovalQry(@RequestBody FitsRepoApprovalDto entity) {
        PageInfo<?> pageInfo = fitsRepoApprovalService.repoApprovalQry(entity);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 质押式回购-审批操作
     * @param processParamDto
     * @return
     */
    @RequestMapping(value = Constants.REPO_APPROVAL)
    public ResponseEntity<?> repoApproval(@RequestBody ProcessParamDto processParamDto) {
        int i = fitsRepoApprovalService.repoApproval(processParamDto);
        return ResponseEntity.ok().body(i);
    }

    /**
     * 质押式回购-重新提交审批
     * @param processParamDto
     * @return
     */
    @RequestMapping(value = Constants.REPO_RESTART_PROCESS)
    public ResponseEntity<?> repoRestartProcess(@RequestBody ProcessParamDto processParamDto) {
        int i = fitsRepoApprovalService.repoRestartProcess(processParamDto);
        return ResponseEntity.ok().body(i);
    }

    /**
     * 质押式回购-取消提交
     * @param processParamDto
     * @return
     */
    @RequestMapping(value = Constants.REPO_CANCEL_PROCESS)
    public ResponseEntity<?> repoCancelProcess(@RequestBody ProcessParamDto processParamDto) {
        int i = fitsRepoApprovalService.repoCancelProcess(processParamDto);
        return ResponseEntity.ok().body(i);
    }
}
