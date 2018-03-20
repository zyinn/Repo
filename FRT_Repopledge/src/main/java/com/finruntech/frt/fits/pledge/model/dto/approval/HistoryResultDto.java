package com.finruntech.frt.fits.pledge.model.dto.approval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Zhangws on 2018/1/3.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResultDto {
	private String userId;        //审批人ID
	private String approvalTime;  //审批时间
	private String approvalStep;  //审批步骤
	private String opinion;       //审批意见
	private String approvalResult;//审批结果
	private String approvalResultNm;//审批结果 0-审批打回, 1-审批同意, 2-审批拒绝
	private String approvaler;//审批人 firstname + lastname

}
