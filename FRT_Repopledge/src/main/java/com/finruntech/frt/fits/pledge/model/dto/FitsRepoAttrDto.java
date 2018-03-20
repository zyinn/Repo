package com.finruntech.frt.fits.pledge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by yinan.zhang on 2018/1/2.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoAttrDto extends FitsPageBaseDto{
    @NotNull(message = "回购市场非空")
    private String raMarket;//回购市场

    private String raRepoCode;// 回购代码

    private String raRepoType;// 回购类型(买断式/质押式)
}
