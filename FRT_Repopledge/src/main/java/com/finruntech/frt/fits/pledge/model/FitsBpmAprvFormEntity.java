package com.finruntech.frt.fits.pledge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Zhangws on 2018/1/3.
 */
@Entity
@Table(name="FRT_FITS_BPM_APRVFORM")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitsBpmAprvFormEntity implements Serializable {
    @Id
    @Column(name="FORM_ID")
    private BigDecimal formId;//审批单记录ID
    @Column(name="FORM_NUM")
    private String formNum;//审批单编号
    @Column(name="FORM_CREATE_DATE")
    private String formCreateDate;//审批单创建日期
    @Column(name="FORM_UPDATE_DATE")
    private String formUpdateDate;//审批单更新日期
    @Column(name="FORM_BEG_DATE")
    private String formBegDate;//审批单有效开始日期
    @Column(name="FORM_END_DATE")
    private String formEndDate;//审批单有效结束日期
    @Column(name="FORM_SUBMIT_DATE")
    private String formSubmitDate;//审批单提交日期
    @Column(name="FORM_TYPE")
    private String formType;//审批单类型
    @Column(name="FORM_BIZTYPE")
    private String formBizType;//业务处理类型
    @Column(name="FORM_STATUS")
    private String formStatus;//审批单状态
    @Column(name="F_PROCESS_NUM")
    private String fProcessNum;//审批流程编号
    @Column(name="F_PPROCESS_STATUS")
    private String fPprocessStatus;//审批流程状态
    @Column(name="F_P_ITEM")
    private String fPItem;//审批流程当前节点
    @Column(name="F_P_ITEMSTATUS")
    private String fPItemstatus;//审批流程当前节点状态
    @Column(name="FORM_REMARKS")
    private String formRemarks;//备注
    @Column(name = "F_DISPLAYATTR")
    private String fDisplayattr;//审批时需要特别展示的风控指标

    public FitsBpmAprvFormEntity(String formNum, String formCreateDate, String formBegDate,
                                 String formSubmitDate, String formType, String formBizType,
                                 String formStatus, String fProcessNum, String fPprocessStatus,String fDisplayattr) {
        this.formNum = formNum;
        this.formCreateDate = formCreateDate;
        this.formBegDate = formBegDate;
        this.formSubmitDate = formSubmitDate;
        this.formType = formType;
        this.formBizType = formBizType;
        this.formStatus = formStatus;
        this.fProcessNum = fProcessNum;
        this.fPprocessStatus = fPprocessStatus;
        this.fDisplayattr=fDisplayattr;
    }

    public FitsBpmAprvFormEntity(String formNum, String formStatus) {
        this.formNum = formNum;
        this.formStatus = formStatus;
    }
}
