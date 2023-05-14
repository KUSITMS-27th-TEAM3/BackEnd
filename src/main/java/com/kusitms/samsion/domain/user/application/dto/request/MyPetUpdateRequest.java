package com.kusitms.samsion.domain.user.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetUpdateRequest {

	private MultipartFile file;
	private String petName;
	private String description;

	@Builder
	public MyPetUpdateRequest(MultipartFile file, String petName, String description) {
		this.file = file;
		this.petName = petName;
		this.description = description;
	}
}
