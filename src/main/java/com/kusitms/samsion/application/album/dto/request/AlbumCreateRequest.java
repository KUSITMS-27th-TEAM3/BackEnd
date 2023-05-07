package com.kusitms.samsion.application.album.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kusitms.samsion.domain.album.entity.Visibility;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AlbumCreateRequest {

	private final String description;
	private final Visibility visibility;
	private final List<MultipartFile> images;


	@Builder
	public AlbumCreateRequest(String description, Visibility visibility, List<MultipartFile> images) {
		this.description = description;
		this.visibility = visibility;
		this.images = images;
	}
}
