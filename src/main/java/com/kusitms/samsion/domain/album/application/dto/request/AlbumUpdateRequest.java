package com.kusitms.samsion.domain.album.application.dto.request;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class AlbumUpdateRequest {

	@Nullable
	private List<String> imageUrlList;
	@Nullable
	private List<MultipartFile> addImageList;

	private String title;
	private String description;

	private List<EmotionTag> emotionTagList;
	private Visibility visibility;

	@Builder
	public AlbumUpdateRequest(List<String> imageUrlList, List<MultipartFile> addImageList, String title,
		String description,
		List<EmotionTag> emotionTagList, Visibility visibility) {
		this.imageUrlList = imageUrlList;
		this.addImageList = addImageList;
		this.title = title;
		this.description = description;
		this.emotionTagList = emotionTagList;
		this.visibility = visibility;
	}
}
