package com.kusitms.samsion.application.user.mapper;

import com.kusitms.samsion.application.user.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.application.user.dto.response.MyPetResponse;
import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.user.entity.MyPet;
import com.kusitms.samsion.domain.user.entity.User;

@Mapper
public class MyPetMapper {

	public MyPetResponse getMyPetInfo(User user){
		MyPet mypet = user.getMypet();
		return MyPetResponse.builder()
			.petName(mypet.getPetName())
			.petImageUrl(mypet.getPetImageUrl())
			.description(mypet.getDescription())
			.build();
	}

	public MyPet updateMyPetInfo(MyPetUpdateRequest request){
		return MyPet.builder()
			.petName(request.getPetName())
			.petImageUrl(request.getPetImageUrl())
			.description(request.getDescription())
			.build();
	}
}
