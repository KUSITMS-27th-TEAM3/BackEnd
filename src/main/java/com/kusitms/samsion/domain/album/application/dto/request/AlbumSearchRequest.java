package com.kusitms.samsion.domain.album.application.dto.request;

import java.util.List;

import org.springframework.lang.Nullable;

import com.kusitms.samsion.domain.album.domain.entity.SortType;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;

import lombok.Getter;

@Getter
public class AlbumSearchRequest {

	@Nullable
	private final List<EmotionTag> emotionTagList;
	@Nullable
	private final SortType sortType;

	public AlbumSearchRequest(List<EmotionTag> emotionTagList, SortType sortType) {
		this.emotionTagList = emotionTagList;
		this.sortType = sortType;
	}


}
