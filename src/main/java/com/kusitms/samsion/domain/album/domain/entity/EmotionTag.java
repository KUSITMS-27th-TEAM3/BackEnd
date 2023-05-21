package com.kusitms.samsion.domain.album.domain.entity;

import lombok.Getter;

@Getter
public enum EmotionTag {

	COZY("아늑함"),
	HAPPY("행복함"),
	JOY("즐거움"),
	MISS("그리움"),
	TOUCHING("감동적"),
	COMFORTABLE("편안함"),
	CHEERFUL("유쾌함"),
	PROUD("자랑스러움"),
	LONELY("외로움"),
	LOVELY("사랑스러움");

	private String description;

	EmotionTag(String description) {
		this.description = description;
	}
}
