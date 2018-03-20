package com.finruntech.frt.fits.pledge.model.dto;


import com.finruntech.frt.fits.pledge.model.FitsAccCustodySecuEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FitsAccCustodySecuDto extends FitsAccCustodySecuEntity {
    private String hsClearingHouseDescription;//清算Description
}
