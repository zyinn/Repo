package com.finruntech.frt.fits.pledge.model.dto;

import lombok.*;

import java.util.List;

/**
 * Created by Zhangws on 2018/1/4.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoApprovalDto extends FitsPageBaseDto{
    //审批查询条件
    private String startDate;
    private String endDate;
    private String userId;
    private String overUserId;
    private String fStatus;
    private List<String> fStatusList;
    private String userName;
}
