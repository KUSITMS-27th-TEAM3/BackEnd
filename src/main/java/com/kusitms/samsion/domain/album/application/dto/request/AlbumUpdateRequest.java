package com.kusitms.samsion.domain.album.application.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AlbumUpdateRequest {

	private List<String> imageUrlList;
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
