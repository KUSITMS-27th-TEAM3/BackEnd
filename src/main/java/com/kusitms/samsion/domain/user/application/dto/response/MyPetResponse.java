package com.kusitms.samsion.domain.user.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetResponse {

	private String userNickname;
	private String petName;
	private String profileImageUrl;
	private String description;

	private String petType;
	private int petAge;

	@Builder
	public MyPetResponse(String userNickname, String petName,  String profileImageUrl, String description, String petType, int petAge) {
		this.petName = petName;
		this.userNickname = userNickname;
		this.profileImageUrl = profileImageUrl;
		this.description = description;
		this.petType = petType;
		this.petAge = petAge;
	}
}
