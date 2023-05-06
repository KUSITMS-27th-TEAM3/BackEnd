package com.kusitms.samsion.application.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPetUpdateRequest {

	String petName;
	String description;

	@Builder
	public MyPetUpdateRequest(String petName, String description) {
		this.petName = petName;
		this.description = description;
	}

}
