package com.kusitms.samsion.common.slice;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PageResponse<T> {

	private final List<T> content;
	private final int pageNumber;
	private final int pageSize;
	private final long totalElements;
	private final int totalPages;
	private final boolean last;

	@Builder
	public PageResponse(List<T> content, int pageNumber, int pageSize, long totalElements, int totalPages,
		boolean last) {
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}


	public static <T> PageResponse<T> of(Page<T> page) {
		return PageResponse.<T>builder()
			.content(page.getContent())
			.pageNumber(page.getNumber())
			.pageSize(page.getSize())
			.totalElements(page.getTotalElements())
			.totalPages(page.getTotalPages())
			.last(page.isLast())
			.build();
	}
}
