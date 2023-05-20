package com.kusitms.samsion.domain.grid.application.dto.request;

import lombok.Getter;

@Getter
public class GridCreateRequest {

	private Long userId;

	public GridCreateRequest(Long userId) {
		this.userId = userId;
	}
}
