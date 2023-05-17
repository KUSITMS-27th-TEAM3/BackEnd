package com.kusitms.samsion.common.slice;

import java.util.List;

import lombok.Getter;

@Getter
public class ListResponse<T> {

	private List<T> content;

	private ListResponse(List<T> content) {
		this.content = content;
	}

	public static <T> ListResponse<T> of(List<T> list) {
		return new ListResponse<>(list);
	}
}
