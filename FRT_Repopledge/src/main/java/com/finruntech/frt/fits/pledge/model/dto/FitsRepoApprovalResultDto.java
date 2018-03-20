package com.finruntech.frt.fits.pledge.model.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by Zhangws on 2018/1/4.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FitsRepoApprovalResultDto{
    private String fFormNum;
    private String aprvformNum;
    private String formStatus;
    private String formStatusName;
    private String fSubmitDate;
    private String psName;
    private String fExecution;
    private String fExecutionName;
    private String raRepoName;
    private String tradeDirection;
    private BigDecimal fAmount;
    private BigDecimal fRepoRate;
    private BigDecimal repoAmount;
    private String fProcessNum;
    private String fPortfacctCash;
    private String fAccName;
    private String entName;
    private String fCpTrader;
    private String fCpCashAcc;
    private String fRepoCode;
    private String fTradeDate;
    private String fTradeDirention;
    private String fPledgeTerm;
    private String fSettleSpeed;
    private String fSettleDate1st;
    private String fSettleDate2nd;
    private String fRemarks;
    private String bmBondName;
    private BigDecimal fTrdSettleAmount;
    private String fSettleType1st;
    private String fSettleType2nd;
    private String traderName;
    private BigDecimal repoRateDeviation;
}
