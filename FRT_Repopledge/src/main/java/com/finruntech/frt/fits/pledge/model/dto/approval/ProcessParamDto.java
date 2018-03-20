package com.finruntech.frt.fits.pledge.model.dto.approval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Zhangws on 2018/1/3.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessParamDto {
	private String userId;
	private String key;
	private String processId;
	private String flag;
	private String option;
	private String variables;
	private String formId;
	private String fDisplayattr;
}
