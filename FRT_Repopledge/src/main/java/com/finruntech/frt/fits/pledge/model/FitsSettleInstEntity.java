package com.finruntech.frt.fits.pledge.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 结算指令表
 *
 */
@Entity
@Setter
@Getter
@Table(name = "FRT_FITS_SETTLE_INST")
public class FitsSettleInstEntity implements Serializable {
	private static final long serialVersionUID = -7189167162738318201L;
	@Id
    @Column(name = "S_ID")
	@JSONField(name ="sId")
	private BigDecimal sId;//结算指令ID
	@Column(name = "S_TYPE")
	@JSONField(name ="sType")
	private String sType           ;//交易指令、非交易指令
	@Column(name = "DEAL_ID")
	@JSONField(name ="dealId")
	private BigDecimal dealId          ;//成交ID
	@Column(name = "S_INSTRUMENT")
	@JSONField(name ="sInstrument")
	private String sInstrument     ;//金融工具编号
	@Column(name = "S_INSTRUMENT_TYPE")
	@JSONField(name ="sInstrumentType")
	private String sInstrumentType ;//金融工具的产品类别，对应下行数据表的MARKETINDICATOR
    @Id
    @Column(name = "SET_DATE")
	@JSONField(name ="setDate")
    private String setDate         ;//理论交割日期
    @Column(name = "SET_DATE_REAL")
	@JSONField(name ="setDateReal")
	private String setDateReal     ;//实际交割日期
	@Column(name = "SCASH_DIRECTION")
	@JSONField(name ="scashDirection")
	private String scashDirection  ;//资金结算方向：进，出，冻结，解冻
	@Column(name = "SSECU_DIRECTION")
	@JSONField(name ="ssecuDirection")
	private String ssecuDirection  ;//证券结算方向：买入，卖出，冻结，解冻，融入，融出
	@Column(name = "PORT_CASH")
	@JSONField(name ="portCash")
	private String portCash        ;//投资组合资金帐户
	@Column(name = "HOST_CASH")
	@JSONField(name ="hostCash")
	private String hostCash        ;//托管资金账户
	@Column(name = "PORT_SECU")
	@JSONField(name ="portSecu")
	private String portSecu        ;//投资组合证券帐户
	@Column(name = "HOST_SECU")
	@JSONField(name ="hostSecu")
	private String hostSecu        ;//托管证券账户
	@Column(name = "SCASH_AMOUNT")
	@JSONField(name ="scashAmount")
	private BigDecimal scashAmount     ;//资金结算金额
	@Column(name = "SSECU_VOLUME")
	@JSONField(name ="ssecuVolume")
	private BigDecimal ssecuVolume     ;//证券结算数量
	@Column(name = "S_STATUS")
	@JSONField(name ="sStatus")
	private String sStatus         ;//确认状态,指令状态未确认、已确认
	@Column(name = "S_PROC_STATUS")
	@JSONField(name ="sProcStatus")
	private String sProcStatus     ;//结算指令已结算处理、未结算处理
	@Column(name = "DEAL_COUNTERPARTY_NUM")
	@JSONField(name ="dealCounterpartyNum")
	private String  dealCounterpartyNum;//交易对手编号
}
