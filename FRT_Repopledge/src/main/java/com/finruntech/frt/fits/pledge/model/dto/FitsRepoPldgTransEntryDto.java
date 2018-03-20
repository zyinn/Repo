package com.finruntech.frt.fits.pledge.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Zhangws on 2018/1/11.
 */
@Setter
@Getter
public class FitsRepoPldgTransEntryDto {
    private String deOrderNum;
    private String pcName;
    private String accTypeName;
    private String entName;
    private String ordCpTrader;
    private String ordCpCashacc;
    private String fExecutionName;
    private String fTradeDate;
    private String raRepoName;
    private String tradeDirection;
    private String ordPledgeTerm;
    private String ordSettleSpeed;
    private String ordSetDate1st;
    private String ordSetDate2nd;
    private BigDecimal ordSettlAmount;
    private BigDecimal ordAmount;
    private BigDecimal ordRepoRate;
    private BigDecimal ordMatureAmount;
    private BigDecimal ordTotalAi;
    private String ordCounterpartyId;
    private String fSettleType1st;
    private String fSettleType2nd;
    private String bmBondName;
    private String ordPledgeRatio;
    private String deHostCash;
    private String deHostSecu;
    private String dePortfolioSecu;
    private String dePortfolioCash;
    private String deRepoCode;
    private String deRepoAmount;
    private String deRepoCount;
    private String deCountparty;
    private String deTradeDirection;
    private String deCpTrader;
    private String deCpCashacc;
    private String deExecutionMarket;
    private String dePledgedBond;
    private String dePledgedBondname;
    private String deSettledate1st;
    private String deSettledate2nd;
}
