package com.kusitms.samsion.domain.album.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AlbumSimpleResponse {

	private final Long id;
	private final String imageUrl;
	private final long empathyCount;
	private final long commentCount;

	@Builder
	public AlbumSimpleResponse(Long id, String imageUrl, long empathyCount, long commentCount) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.empathyCount = empathyCount;
		this.commentCount = commentCount;
	}
}
