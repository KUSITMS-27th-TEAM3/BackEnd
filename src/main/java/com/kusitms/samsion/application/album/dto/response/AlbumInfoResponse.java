package com.kusitms.samsion.application.album.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함
 */
@Getter
public class AlbumInfoResponse {

	private final List<String> imageUrlList;
	private final String description;

	private final String writer;
	private final String writerProfileImageUrl;

	@Builder
	public AlbumInfoResponse(List<String> imageUrlList, String description, String writer, String writerProfileImageUrl) {
		this.imageUrlList = imageUrlList;
		this.description = description;
		this.writer = writer;
		this.writerProfileImageUrl = writerProfileImageUrl;
	}
}
