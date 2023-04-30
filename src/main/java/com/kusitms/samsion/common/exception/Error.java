package com.kusitms.samsion.common.exception;

import lombok.Getter;

@Getter
public enum Error {

	// JWT
	INVALID_TOKEN("잘못된 토큰 요청", 401),
	EXPIRED_TOKEN("토큰 만료", 402);

	private final String message;
	private final int errorCode;

	Error(String message, int errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}
}
