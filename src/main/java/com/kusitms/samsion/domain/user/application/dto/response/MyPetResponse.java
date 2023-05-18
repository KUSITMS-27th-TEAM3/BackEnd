package com.kusitms.samsion.domain.user.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetResponse {

	private String petName;
	private String petImageUrl;
	private String description;

	private String petType;
	private int petAge;

	@Builder
	public MyPetResponse(String petName, String petImageUrl, String description, String petType, int petAge) {
		this.petName = petName;
		this.petImageUrl = petImageUrl;
		this.description = description;
		this.petType = petType;
		this.petAge = petAge;
	}
}
