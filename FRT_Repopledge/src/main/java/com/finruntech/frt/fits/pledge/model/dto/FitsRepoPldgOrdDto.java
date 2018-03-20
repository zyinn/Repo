package com.finruntech.frt.fits.pledge.model.dto;

import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgOrdEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by weihubin on 2017/12/27.
 */
@Setter
@Getter
public class FitsRepoPldgOrdDto extends FitsRepoPldgOrdEntity{
    private String startDate; //开始时间
    private String endDate; //结束时间
    private String ordInitiatorName;//指令发起人名称
    private String cpEntName;//交易对手 机构名称
    private String entName;//机构名称
    /**
     * 资金账户信息
     */
    private String hcAccNumber;
    private String hcAccName;
    private String hcAccBankName;
    private String hcAccBankLarge;
    /**
     * 证券账户账户信息
     */
    private String hsAccNumber;
    private String hsAccName;
    private String hsClearingHouse;
    private String hsClearingHouseDescription;

    private String psAcctClass;
    private BigDecimal pledgeRatioVal;

    private String raRepoName;//回购名称
    private String bmBondCode;//质押券代码
    private String bmBondName;//质押券名称
    private String setType1stName;//结算方式名称
    private String setType2ndName;//结算方式名称

    private String portacctCashName;//投资组合名称
    private String ordTradeDirectionName;//交易方向


    private String fInitiatorName;//指令发起人名称
    private String fTradeDate;//交易日期

    private List<FitsRepoPldgMgtEntity> pldgMgtList;//质押券


}
