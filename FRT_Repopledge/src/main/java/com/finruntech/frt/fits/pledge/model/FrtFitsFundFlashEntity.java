package com.finruntech.frt.fits.pledge.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
* FrtFitsFundFlashEntity
* @author zyinn
* @date 2018/01/26 13:51:49
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(FrtFitsFundFlashEntity.FrtFitsFundFlashEntityPK.class)
@Table(name = "FRT_FITS_FUND_FLASH")
public class FrtFitsFundFlashEntity {
	@Id
	@Column(name = "FF_DATE")
	@JSONField(name ="ffDate")
	private String ffDate;//业务日期
	@Id
	@Column(name = "FF_CUSTODY_SECU")
	@JSONField(name ="ffCustodySecu")
	private String ffCustodySecu;//簿记证券帐户

	@Column(name = "FF_AVAILABLE_AMOUNT")
	@JSONField(name ="ffAvailableAmount")
	private java.math.BigDecimal ffAvailableAmount;//当日资金可用金额（当日可用余额=当日帐户资金余额 - 资金冻结金额）

	@Column(name = "FF_FREEZE_AMOUNT")
	@JSONField(name ="ffFreezeAmount")
	private java.math.BigDecimal ffFreezeAmount;//当日资金冻结金额（当日资金冻结金额=当日资金冻结余额 + 新资金冻结金额（来自DTL表））
	@Column(name = "FF_INIT_AMOUNT")
	@JSONField(name ="ffInitAmount")
	private java.math.BigDecimal ffInitAmount;//当日期初金额(前日余额结转，为当日期初金额)
	@Id
	@Column(name = "FF_PORTFOLIO_SECU")
	@JSONField(name ="ffPortfolioSecu")
	private String ffPortfolioSecu;//投资组合证券帐户
	@Column(name = "FF_FREEZE_TRDINGAMT")
	@JSONField(name ="ffFreezeTrdingamt")
	private java.math.BigDecimal ffFreezeTrdingamt;//当日在途交易冻结金额（未交易指令、委托上的冻结资金）
	@Id
	@Column(name = "FF_PORTFOLIO_CASH")
	@JSONField(name ="ffPortfolioCash")
	private String ffPortfolioCash;//投资组合资金帐户
	@Column(name = "FF_AMOUNT")
	@JSONField(name ="ffAmount")
	private java.math.BigDecimal ffAmount;//当日帐户资金余额(当日帐户累计，初始=当日期初金额；当日帐户资金余额 = 当日帐户资金余额 +或- 流入或流出金额（来自DTL表）)
	@Id
	@Column(name = "FF_CUSTODY_CASH")
	@JSONField(name ="ffCustodyCash")
	private String ffCustodyCash;//簿记资金帐户
	@Id
	@Column(name = "FF_CURRENCY")
	@JSONField(name ="ffCurrency")
	private String ffCurrency;//币种

	@AllArgsConstructor
	@NoArgsConstructor
	@EqualsAndHashCode
	public static class FrtFitsFundFlashEntityPK implements Serializable {
		private static final long serialVersionUID = -9031512642696449018L;
		@Id
		@Column(name = "FF_DATE")
		@JSONField(name ="ffDate")
		private String ffDate;//业务日期
		@Id
		@Column(name = "FF_CUSTODY_SECU")
		@JSONField(name ="ffCustodySecu")
		private String ffCustodySecu;//簿记证券帐户
		@Id
		@Column(name = "FF_PORTFOLIO_SECU")
		@JSONField(name ="ffPortfolioSecu")
		private String ffPortfolioSecu;//投资组合证券帐户
		@Id
		@Column(name = "FF_PORTFOLIO_CASH")
		@JSONField(name ="ffPortfolioCash")
		private String ffPortfolioCash;//投资组合资金帐户
		@Id
		@Column(name = "FF_CUSTODY_CASH")
		@JSONField(name ="ffCustodyCash")
		private String ffCustodyCash;//簿记资金帐户
		@Id
		@Column(name = "FF_CURRENCY")
		@JSONField(name ="ffCurrency")
		private String ffCurrency;//币种
	}
}
