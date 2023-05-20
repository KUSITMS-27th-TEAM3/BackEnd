package com.kusitms.samsion.domain.album.application.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class AlbumImageUpdateRequest {

	private final Long albumId;
	private final List<String> imageUrlList;
	private final List<MultipartFile> addImageList;

	public AlbumImageUpdateRequest(Long albumId, List<String> imageUrlList, List<MultipartFile> addImageList) {
		this.albumId = albumId;
		this.imageUrlList = imageUrlList;
		this.addImageList = addImageList;
	}
}
