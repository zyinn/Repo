package com.finruntech.frt.fits.pledge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yinan.zhang on 2018/1/10.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoPldgMgtDto {
    private String subJectNum;//指令编号/委托编号/成交编号
    private String subJectType;//指令/委托/成交(INST/ORD/DEAL)
    private String pRepoCode;// 质押式回购代码
}
