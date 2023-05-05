package com.kusitms.samsion.application.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetUpdateRequest {

	String petName;
	String petImageUrl;
	String description;

	@Builder
	public MyPetUpdateRequest(String petName, String petImageUrl, String description) {
		this.petName = petName;
		this.petImageUrl = petImageUrl;
		this.description = description;
	}

}
