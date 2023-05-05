package com.kusitms.samsion.common.exception;

import lombok.Getter;

@Getter
public enum Error {

	// 사용자
	USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 100),


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
