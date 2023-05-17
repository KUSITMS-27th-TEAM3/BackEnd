package com.kusitms.samsion.domain.grid.application.dto.request;

import lombok.Getter;

@Getter
public class GridCountUpdateRequest {

	private Long userId;

	public GridCountUpdateRequest(Long userId) {
		this.userId = userId;
	}
}
