package com.finruntech.frt.fits.pledge.model.dto.approval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnJsonDto {
	private String status;
	private String describe;
	private ResultDto result;
}
