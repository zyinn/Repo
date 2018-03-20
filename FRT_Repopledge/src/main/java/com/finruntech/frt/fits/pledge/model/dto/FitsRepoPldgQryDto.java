package com.finruntech.frt.fits.pledge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Zhangws on 2018/1/18.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoPldgQryDto {
    private String pcName;
    private String pRepoTrddate;
    private String raRepoCode;
    private String raRepoName;
    private BigDecimal pRepoRate;
    private BigDecimal pNetAmount;
    private BigDecimal pReturnAmount;
    private BigDecimal pProfit;
    private String tradeDirection;
    private String pSetlSpeed;
    private String pEndSetldate;
    private String entName;
    private BigDecimal pDailyintst;
    private BigDecimal pAccrintst;
    private String fSettleType1st;
    private String fSettleType2nd;
}
