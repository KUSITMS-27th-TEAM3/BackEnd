package com.kusitms.samsion.domain.album.domain.entity;

import lombok.Getter;

@Getter
public enum Visibility {
	PUBLIC("공개"),
	PRIVATE("비공개");

	private String description;

	Visibility(String description) {
		this.description = description;
	}
}
