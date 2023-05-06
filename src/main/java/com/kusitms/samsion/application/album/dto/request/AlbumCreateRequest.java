package com.kusitms.samsion.application.album.dto.request;

import com.kusitms.samsion.domain.album.entity.Visibility;

import lombok.Getter;

@Getter
public class AlbumCreateRequest {

	private final String description;
	private final Visibility visibility;

	public AlbumCreateRequest(String description, Visibility visibility) {
		this.description = description;
		this.visibility = visibility;
	}
}
