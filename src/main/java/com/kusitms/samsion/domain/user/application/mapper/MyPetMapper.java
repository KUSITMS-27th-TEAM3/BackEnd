package com.kusitms.samsion.domain.user.application.mapper;

import com.kusitms.samsion.domain.user.application.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.user.domain.entity.MyPet;
import com.kusitms.samsion.domain.user.domain.entity.User;

@Mapper
public class MyPetMapper {

	public static MyPetResponse mapToMyPetResponse(User user){
		MyPet mypet = user.getMypet();
		return MyPetResponse.builder()
			.petName(mypet.getPetName())
			.petImageUrl(mypet.getPetImageUrl())
			.description(mypet.getDescription())
			.build();
	}

	public static MyPet mapToMyPetUpdateRequest(MyPetUpdateRequest request, String imageUrl){
		return MyPet.builder()
			.petName(request.getPetName())
			.petImageUrl(imageUrl)
			.description(request.getDescription())
			.build();
	}
}
