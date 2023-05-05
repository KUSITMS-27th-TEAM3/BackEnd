package com.kusitms.samsion.common.exception.dto;

import com.kusitms.samsion.common.exception.BusinessException;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final int errorCode;


	private ErrorResponse(int errorCode) {
		this.errorCode = errorCode;
	}

	public static ErrorResponse from(BusinessException e){
		return new ErrorResponse(e.getError().getErrorCode());
	}
}
