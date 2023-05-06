package com.kusitms.samsion.application.album.dto.response;

import lombok.Getter;

@Getter
public class AlbumSimpleResponse {

	private final Long id;
	private final String imageUrl;

	public AlbumSimpleResponse(Long id, String imageUrl) {
		this.id = id;
		this.imageUrl = imageUrl;
	}
}
