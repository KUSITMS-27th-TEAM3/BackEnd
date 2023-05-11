package com.kusitms.samsion.common.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.kusitms.samsion.common.slice.PageResponse;

public class SliceTestUtils<T> {

	public static <T> PageResponse<T> getMockPageResponse(T dto){
		return PageResponse.<T>builder()
			.content(List.of(dto))
			.pageNumber(0)
			.pageSize(20)
			.totalElements(1)
			.totalPages(1)
			.last(false)
			.build();
	}

	public static Pageable getMockPageable(){
		return PageRequest.of(0,10);
	}

	public static <T> Page<T> getMockPage(List<T> list){
		return new PageImpl<T>(list);
	}
}
