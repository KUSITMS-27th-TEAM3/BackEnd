package com.kusitms.samsion.application.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetResponse {

	String petName;
	String petImageUrl;
	String description;

	@Builder
	public MyPetResponse(String petName, String petImageUrl, String description) {
		this.petName = petName;
		this.petImageUrl = petImageUrl;
		this.description = description;
	}
}
