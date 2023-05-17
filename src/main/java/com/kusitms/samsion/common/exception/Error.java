package com.kusitms.samsion.common.exception;

import lombok.Getter;

@Getter
public enum Error {

	// 사용자
	USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 1000),

	// 앨범
	ALBUM_NOT_FOUND("앨범을 찾을 수 없습니다.", 2000),
	ALBUM_ACCESS_DENIED("앨범에 접근할 수 없습니다.", 2001),
	FILE_EXTENTION_ERROR("잘못된 형식의 파일입니다.", 2002),
	FILE_UPLOAD_ERROR("파일 업로드에 실패했습니다.", 2003),
	FILE_DELETE_ERROR("파일 삭제에 실패했습니다.", 2004),

	// 공감
	EMPATHY_NOT_FOUND("공감을 찾을 수 없습니다.", 3000),


	// 질문
	QUESTION_NOT_FOUND("질문을 찾을 수 없습니다.", 4000),
	ANSWER_NOT_FOUND("답변을 찾을 수 없습니다.", 4001),
	ANSWER_ALREADY_EXIST("이미 답변이 존재합니다.", 4002),

	//댓글,
	COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다.", 5000),

	// 그리드
	GRID_NOT_REGISTERED("그리드를 찾을 수 없습니다.", 6000),
	GRID_ALREADY_REGISTERED("이미 그리드가 존재합니다.", 6001),

	// JWT
	INVALID_TOKEN("잘못된 토큰 요청", 7000),
	EXPIRED_TOKEN("토큰 만료", 7001);

	private final String message;
	private final int errorCode;

	Error(String message, int errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}
}
