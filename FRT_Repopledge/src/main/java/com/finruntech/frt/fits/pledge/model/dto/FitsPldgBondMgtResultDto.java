package com.finruntech.frt.fits.pledge.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by yinan.zhang on 2018/1/4.
 */
@Setter
@Getter
public class FitsPldgBondMgtResultDto extends FitsRepoPldgBondMgtEntityDto {
    private BigDecimal bPriceDirty;
}
