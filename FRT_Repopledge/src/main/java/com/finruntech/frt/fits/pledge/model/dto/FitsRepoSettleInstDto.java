package com.finruntech.frt.fits.pledge.model.dto;

import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.FitsSettleInstEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by weihubin on 2018/1/10.
 */
@Setter
@Getter
public class FitsRepoSettleInstDto extends FitsSettleInstEntity {
    private BigDecimal repoRate;
    private String  tradeDirectionName;
    private String  sStatusName;//确认状态名称
    private String  psName;//投资组合名称
    private String  raRepoName;//回购名称
    private String deCounterpartyName;//交易对手名称
    private String deNum;//成交编号
    private String deExeidNum;//成交回报编号
    private BigDecimal instAmount;//回购券面额
    private BigDecimal matureAmount;//理论到期还款金额
    private BigDecimal instSettleAmount;//交易金额
    private String deSettleDate1st;//首次结算日期
    private String deSettleDate2nd;//到期结算日期
    private String fTradeDate;//交易日期
    private String sProcType;//结算类型,日间1,日终2
    private String deRepoCode;
    private List<FitsRepoPldgMgtEntity> pldgMgtList;//质押券
}
