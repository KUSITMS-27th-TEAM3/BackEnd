package com.kusitms.samsion.domain.album.application.dto.response;

import java.util.List;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * dto 캐싱할려면 기본생성자 만들어야함
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlbumInfoResponse {

	private List<String> imageUrlList;
	private String title;
	private String description;
	private Visibility visibility;

	private String writer;
	private String petName;
	private String writerProfileImageUrl;
	private long commentCount;
	private long empathyCount;
	private List<EmotionTag> emotionTagList;


	@Builder
	public AlbumInfoResponse(List<String> imageUrlList, String title, String description, Visibility visibility, String writer, String petName, String writerProfileImageUrl,
		long commentCount, long empathyCount, List<EmotionTag> emotionTagList) {
		this.imageUrlList = imageUrlList;
		this.title = title;
		this.description = description;
		this.visibility = visibility;
		this.writer = writer;
		this.petName = petName;
		this.writerProfileImageUrl = writerProfileImageUrl;
		this.commentCount = commentCount;
		this.empathyCount = empathyCount;
		this.emotionTagList = emotionTagList;
	}
}
