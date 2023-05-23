package com.kusitms.samsion.domain.album.application.dto.request;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.SortType;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
public class AlbumSearchRequest {

	@Nullable
	private final List<EmotionTag> emotionTagList;
	private final SortType sortType;

	public AlbumSearchRequest(List<EmotionTag> emotionTagList, SortType sortType) {
		this.emotionTagList = emotionTagList;
		this.sortType = sortType;
	}


}
