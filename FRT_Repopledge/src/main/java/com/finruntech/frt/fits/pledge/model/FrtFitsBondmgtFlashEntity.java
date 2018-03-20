package com.finruntech.frt.fits.pledge.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
* FrtFitsBondmgtFlashEntity
* @author zyinn
* @date 2018/01/26 13:42:28
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(FrtFitsBondmgtFlashEntity.FrtFitsBondmgtFlashEntityPK.class)
@Table(name = "FRT_FITS_BONDMGT_FLASH")
public class FrtFitsBondmgtFlashEntity {
	@Column(name = "BM_BOND_LEND")
	@JSONField(name ="bmBondLend")
	private java.math.BigDecimal bmBondLend;//融入融出数量
	@Id
	@Column(name = "BM_DATE")
	@JSONField(name ="bmDate")
	private String bmDate;//业务日期
	@Column(name = "BM_BNDLND_SETLINGAMT")
	@JSONField(name ="bmBndlndSetlingamt")
	private java.math.BigDecimal bmBndlndSetlingamt;//债券借贷结算在途数量（T+n待交割数量）
	@Column(name = "BM_BNDFWD_SETLINGAMT")
	@JSONField(name ="bmBndfwdSetlingamt")
	private java.math.BigDecimal bmBndfwdSetlingamt;//债券远期结算在途券数量
	@Id
	@Column(name = "BM_BOND_CODE")
	@JSONField(name ="bmBondCode")
	private String bmBondCode;//债券代码
	@Column(name = "BM_BOND_NAME")
	@JSONField(name ="bmBondName")
	private String bmBondName;//债券名称
	@Column(name = "BM_BOND_FORWARD")
	@JSONField(name ="bmBondForward")
	private java.math.BigDecimal bmBondForward;//债券远期券数量
	@Id
	@Column(name = "BM_PORTFOLIO_CASH")
	@JSONField(name ="bmPortfolioCash")
	private String bmPortfolioCash;//组合资金帐号
	@Id
	@Column(name = "BM_HOST_SECU")
	@JSONField(name ="bmHostSecu")
	private String bmHostSecu;//托管证券帐号
	@Column(name = "BM_PLDG_TRDINGAMT")
	@JSONField(name ="bmPldgTrdingamt")
	private java.math.BigDecimal bmPldgTrdingamt;//质押式回购交易在途数量（未成交指令/委托待交易数量）
	@Column(name = "BM_BOND_OUTRIGHT")
	@JSONField(name ="bmBondOutright")
	private java.math.BigDecimal bmBondOutright;//买断式回购债券数量
	@Column(name = "BM_BNDFWD_TRDINGAMT")
	@JSONField(name ="bmBndfwdTrdingamt")
	private java.math.BigDecimal bmBndfwdTrdingamt;//债券远期交易在途券数量
	@Id
	@Column(name = "BM_HOST_CASH")
	@JSONField(name ="bmHostCash")
	private String bmHostCash;//托管资金帐号
	@Column(name = "BM_OTRT_TRDINGAMT")
	@JSONField(name ="bmOtrtTrdingamt")
	private java.math.BigDecimal bmOtrtTrdingamt;//买断式回购交易在途数量（未成交指令/委托待交易数量）
	@Column(name = "BM_BOND_PLEDGE")
	@JSONField(name ="bmBondPledge")
	private java.math.BigDecimal bmBondPledge;//质押式回购债券数量
	@Column(name = "BM_BOND_BS")
	@JSONField(name ="bmBondBs")
	private java.math.BigDecimal bmBondBs;//自有数量(现券买卖)
	@Column(name = "BM_OTRT_SETLINGAMT")
	@JSONField(name ="bmOtrtSetlingamt")
	private java.math.BigDecimal bmOtrtSetlingamt;//买断式回购结算在途数量（T+n待交割数量）
	@Column(name = "BM_BND_TRDINGAMT")
	@JSONField(name ="bmBndTrdingamt")
	private java.math.BigDecimal bmBndTrdingamt;//现券买卖交易在途数量（未成交指令/委托待交易数量）
	@Column(name = "BM_BND_SETLINGAMT")
	@JSONField(name ="bmBndSetlingamt")
	private java.math.BigDecimal bmBndSetlingamt;//现券买卖结算在途数量（T+n待交割数量）
	@Id
	@Column(name = "BM_PORTFOLIO_SECU")
	@JSONField(name ="bmPortfolioSecu")
	private String bmPortfolioSecu;//组合证券帐号
	@Column(name = "BM_BNDLND_TRDINGAMT")
	@JSONField(name ="bmBndlndTrdingamt")
	private java.math.BigDecimal bmBndlndTrdingamt;//债券借贷交易在途数量（未成交指令/委托待交易数量）
	@Column(name = "BM_PLDG_SETLINGAMT")
	@JSONField(name ="bmPldgSetlingamt")
	private java.math.BigDecimal bmPldgSetlingamt;//质押式回购结算在途数量（T+n待交割数量）
	@Column(name = "BM_BOND_AVAILABLE")
	@JSONField(name ="bmBondAvailable")
	private java.math.BigDecimal bmBondAvailable;//可用数量

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@EqualsAndHashCode
	public static class FrtFitsBondmgtFlashEntityPK implements Serializable {
		private static final long serialVersionUID = -9031512642696449018L;
		@Id
		@Column(name = "BM_DATE")
		@JSONField(name ="bmDate")
		private String bmDate;//业务日期
		@Id
		@Column(name = "BM_BOND_CODE")
		@JSONField(name ="bmBondCode")
		private String bmBondCode;//债券代码
		@Id
		@Column(name = "BM_PORTFOLIO_CASH")
		@JSONField(name ="bmPortfolioCash")
		private String bmPortfolioCash;//组合资金帐号
		@Id
		@Column(name = "BM_HOST_SECU")
		@JSONField(name ="bmHostSecu")
		private String bmHostSecu;//托管证券帐号
		@Id
		@Column(name = "BM_HOST_CASH")
		@JSONField(name ="bmHostCash")
		private String bmHostCash;//托管资金帐号
		@Id
		@Column(name = "BM_PORTFOLIO_SECU")
		@JSONField(name ="bmPortfolioSecu")
		private String bmPortfolioSecu;//组合证券帐号
	}
}
