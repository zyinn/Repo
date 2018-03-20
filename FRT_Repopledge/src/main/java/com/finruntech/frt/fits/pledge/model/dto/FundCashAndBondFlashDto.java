package com.finruntech.frt.fits.pledge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Zhangws on 2018/2/1.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundCashAndBondFlashDto {
    private String portfolioSecu;//组合证券帐号
    private String custodySecu;
    private String portfolioCash;//组合资金帐号
    private String custodyCash;
    private String currency;//币种
    private String bmBondCode;//债券代码
    private BigDecimal freezeTrdingamt;//当日在途交易冻结金额（未交易指令、委托上的冻结资金）
    private String bmBndTrdinam;//在途数量
    private String flag; //1现券2买断式3债券借贷4质押式5债券远期
    private String direction;//交易方向 1买2卖B质押式正回购S逆回购
}
