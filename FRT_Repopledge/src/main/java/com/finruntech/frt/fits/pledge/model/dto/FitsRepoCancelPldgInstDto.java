package com.finruntech.frt.fits.pledge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/8.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoCancelPldgInstDto {
    /**
     * 审批提交传三个字段：formId fAmount fInstStatus
     * 取消提交：formId processId flag userId fInstStatus
     */
    private String processId;
    private String formId;//审批单id :APRVFORM_NUM
    private String flag;// 取消提交 传：3
    private BigDecimal fAmount;//提交时传 交易金额：fTrdSettleAmount
    private String userId;//服务端字段，web无需传
    private String fInstStatus;//指令单状态 ，7：审批打回
    private BigDecimal repoRateDeviation;//回购利率偏离度
    private Boolean leaderApprFlg;//投资经理审批标志
}
