package com.finruntech.frt.fits.pledge.model.dto;

import com.finruntech.frt.fits.pledge.model.FitsRepoPldgInstEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lenovo on 2018/1/4.
 */
@Setter
@Getter
public class RepoInstDto extends FitsRepoPldgInstEntity {
    private static final long serialVersionUID = 2318406672003966415L;
    private String fInitiatorName; //指令发起人
    private String executorName; //执行人
    private String fTradeDirectionName; //交易方向显示名称
    private String fPortfacctCashName; //投资组合
    private String startDate; //开始时间
    private String endDate; //结束时间
    private List<String> fInstStatusList;//指令状态列表
    private String bmBondName;//
    private String fInstStatusName;//指令状态
    private String deCounterpartyName;//交易对手名称
    private String raRepoName;//回购名称
    private String fExecutionName;//执行市场名称
}
