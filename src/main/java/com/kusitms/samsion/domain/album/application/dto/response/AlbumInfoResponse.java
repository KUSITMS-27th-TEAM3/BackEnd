package com.kusitms.samsion.domain.album.application.dto.response;

import java.util.List;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;

import lombok.Builder;
import lombok.Getter;

/**
 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함
 */
@Getter
public class AlbumInfoResponse {

	private final List<String> imageUrlList;
	private final String title;
	private final String description;

	private final String writer;
	private final String petName;
	private final String writerProfileImageUrl;

	private final long commentCount;
	private final long empathyCount;

	private final List<EmotionTag> emotionTagList;

	@Builder
	public AlbumInfoResponse(List<String> imageUrlList, String title, String description, String writer, String petName, String writerProfileImageUrl,
		long commentCount, long empathyCount, List<EmotionTag> emotionTagList) {
		this.imageUrlList = imageUrlList;
		this.title = title;
		this.description = description;
		this.writer = writer;
		this.petName = petName;
		this.writerProfileImageUrl = writerProfileImageUrl;
		this.commentCount = commentCount;
		this.empathyCount = empathyCount;
		this.emotionTagList = emotionTagList;
	}
}
