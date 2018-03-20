package com.finruntech.frt.fits.pledge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yinan.zhang on 2018/1/5.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FitsRepoPldgQueryInstDto extends FitsPageBaseDto{
    private String startDate;
    private String endDate;
    private String fPortfAcctSecu;

    public String getFPortfAcctSecu() {
        return fPortfAcctSecu;
    }

    public void setFPortfAcctSecu(String fPortfAcctSecu) {
        this.fPortfAcctSecu = fPortfAcctSecu;
    }
}
