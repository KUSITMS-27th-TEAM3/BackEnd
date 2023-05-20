package com.kusitms.samsion.domain.user.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetUpdateRequest {

	private String userNickname;
	private String description;
	private String petType;
	private int petAge;
	private String petName;

	private MultipartFile profileImage;
	private MultipartFile petImage;

	@Builder
	public MyPetUpdateRequest(String userNickname, String description, String petType, String petAge, String petName,
		MultipartFile profileImage, MultipartFile petImage) {
		this.userNickname = userNickname;
		this.description = description;
		this.petType = petType;
		this.petAge = Integer.parseInt(petAge);
		this.petName = petName;
		this.profileImage = profileImage;
		this.petImage = petImage;
	}
}
