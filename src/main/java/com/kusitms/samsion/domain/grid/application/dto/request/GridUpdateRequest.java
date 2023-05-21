package com.kusitms.samsion.domain.grid.application.dto.request;

import lombok.Getter;

@Getter
public class GridUpdateRequest {

	private Long userId;
	private String petImageUrl;

	public GridUpdateRequest(Long userId, String petImageUrl) {
		this.userId = userId;
		this.petImageUrl = petImageUrl;
	}
}
