package com.kusitms.samsion.common.exception;

import lombok.Getter;

@Getter
public enum Error {

	// 사용자
	USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 1000),

	// 앨범
	ALBUM_NOT_FOUND("앨범을 찾을 수 없습니다.", 2000),
	ALBUM_ACCESS_DENIED("앨범에 접근할 수 없습니다.", 2001),

	// JWT
	INVALID_TOKEN("잘못된 토큰 요청", 4001),
	EXPIRED_TOKEN("토큰 만료", 4002);

	private final String message;
	private final int errorCode;

	Error(String message, int errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}
}
