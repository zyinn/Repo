package com.finruntech.frt.fits.pledge.repository;

import com.finruntech.frt.fits.pledge.model.dto.FitsRepoApprovalResultDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhangws on 2018/1/3.
 */
@Repository("fitsRepoApprovalMapper")
public interface FitsRepoApprovalMapper<T> {
    /**
     * 查询Approval
     * @param t T
     * @return list FitsRepoApprovalResultDto
     */
    List<FitsRepoApprovalResultDto> queryFitsRepoApprovalList(T t);
}
