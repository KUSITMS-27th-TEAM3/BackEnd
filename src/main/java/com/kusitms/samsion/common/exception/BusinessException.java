package com.kusitms.samsion.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

	private final Error error;

	public BusinessException(Error error) {
		this.error = error;
	}



}
