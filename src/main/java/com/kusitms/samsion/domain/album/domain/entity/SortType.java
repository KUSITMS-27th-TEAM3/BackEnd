package com.kusitms.samsion.domain.album.domain.entity;

import lombok.Getter;

@Getter
public enum SortType {

	DEFAULT("createdDate"),
	COMMENT("comments"),
	EMPATHY("empathies");

	private final String type;

	SortType(String type){
		this.type = type;
	}

}
