package com.kusitms.samsion.domain.grid.application.dto.response;

import lombok.Getter;

@Getter
public class StampResponse {

	private final Long stampId;
	private final String imageUrl;

	public StampResponse(Long stampId, String imageUrl) {
		this.stampId = stampId;
		this.imageUrl = imageUrl;
	}

}
