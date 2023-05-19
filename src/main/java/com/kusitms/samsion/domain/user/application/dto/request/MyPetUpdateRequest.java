package com.kusitms.samsion.domain.user.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetUpdateRequest {

	private String description;
	private String petType;
	private int petAge;
	private String petName;
	private String petNickname;

	private MultipartFile profileImage;
	private MultipartFile petImage;

	@Builder
	public MyPetUpdateRequest(String petNickname, String description, String petType, int petAge, String petName,
		MultipartFile profileImage, MultipartFile petImage) {
		this.petNickname = petNickname;
		this.description = description;
		this.petType = petType;
		this.petAge = petAge;
		this.petName = petName;
		this.profileImage = profileImage;
		this.petImage = petImage;
	}
}
