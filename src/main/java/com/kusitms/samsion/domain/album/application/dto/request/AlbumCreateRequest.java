package com.kusitms.samsion.domain.album.application.dto.request;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class AlbumCreateRequest {


	private final String title;
	private final String description;
	private final Visibility visibility;
	private final List<MultipartFile> albumImages;
	private final List<EmotionTag> emotionTags;

	@Builder
	public AlbumCreateRequest(String title, String description, Visibility visibility, List<MultipartFile> albumImages,
		List<EmotionTag> emotionTags) {
		this.title = title;
		this.description = description;
		this.visibility = visibility;
		this.albumImages = albumImages;
		this.emotionTags = emotionTags;
	}
}
