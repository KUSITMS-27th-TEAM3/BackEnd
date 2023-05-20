package com.kusitms.samsion.domain.album.application.dto.request;

import java.util.List;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;

import lombok.Getter;

@Getter
public class TagUpdateRequest {

	private final Long albumId;
	private final List<EmotionTag> tagList;

	public TagUpdateRequest(Long albumId, List<EmotionTag> tagList) {
		this.albumId = albumId;
		this.tagList = tagList;
	}
}
