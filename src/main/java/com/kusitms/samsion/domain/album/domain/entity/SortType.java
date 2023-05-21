package com.kusitms.samsion.domain.album.domain.entity;

import lombok.Getter;

@Getter
public enum SortType {

	DEFAULT("생성순"),
	COMMENT("댓글수 순"),
	EMPATHY("공감수 순");

	private final String description;

	SortType(String description){
		this.description = description;
	}

}
