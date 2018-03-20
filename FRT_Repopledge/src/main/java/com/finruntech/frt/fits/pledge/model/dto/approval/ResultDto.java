package com.finruntech.frt.fits.pledge.model.dto.approval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
	private String processId;
	private String processNextNode;
	private String processStatus;
}
