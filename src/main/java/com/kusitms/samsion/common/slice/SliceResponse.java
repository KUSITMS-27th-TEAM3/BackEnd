package com.kusitms.samsion.common.slice;

import java.util.List;

import org.springframework.data.domain.Slice;

import lombok.Getter;

@Getter
public class SliceResponse<T> {

	private final List<T> content;
	private final int page;
	private final int size;
	private final boolean hasNext;

	private SliceResponse(List<T> content, int page, int size, boolean hasNext) {
		this.content = content;
		this.page = page;
		this.size = size;
		this.hasNext = hasNext;
	}

	public static <T> SliceResponse<T> of(Slice<T> slice) {
		return new SliceResponse<>(slice.getContent(), slice.getNumber(), slice.getSize(), slice.hasNext());
	}
}
