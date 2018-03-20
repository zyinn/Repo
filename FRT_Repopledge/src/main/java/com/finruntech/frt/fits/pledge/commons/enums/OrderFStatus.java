package com.finruntech.frt.fits.pledge.commons.enums;

/**
 * Created by lenovo on 2017/5/18.
 * 保存 - 新建
 * 保存并提交 - 待审批
 * 如果无需审批（比如<5000万） - 无需审批
 * 如果需要审批（比如>5000万） - 待审批
 * 领导点击“指令审批” - 审批中， P_STATUS同时置为 挂起
 * 审批通过 - 审批通过
 * 审批拒绝 - 审批拒绝
 * 审批打回 - 打回
 *
 */
public enum OrderFStatus {

    NEW("新建","1"),
    PENDING("待审批","2"),
    APPROVALING("审批中","3"),
    APPROVED("审批通过","4"),
    APPROVALREFUSED("审批拒绝","5"),
    NOAPPROVAL("无需审批","6"),
    APPROVALROLLBACK("审批打回","7"),
	EXECUTION("执行中","8"),
	INVALID("已失效","9");
	
    private String displayName;
    private String code;

    OrderFStatus(String name, String code) {
        this.displayName = name;
        this.code = code;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getCode() {
        return code;
    }
}
