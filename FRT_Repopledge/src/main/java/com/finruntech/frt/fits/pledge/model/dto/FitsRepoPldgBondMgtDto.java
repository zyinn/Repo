package com.finruntech.frt.fits.pledge.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by yinan.zhang on 2017/12/28.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoPldgBondMgtDto extends FitsPageBaseDto{
    //组合证券帐号
    @NotNull(message = "组合证券帐号非空")
    private String bmPortfolioSecu;
    //会计分类
    private String psAcctClass;

    //组合资金帐号
    @NotNull(message = "组合资金帐号非空")
    private String bmPortfolioCash;

    private String currentDate;//当前日期

}
