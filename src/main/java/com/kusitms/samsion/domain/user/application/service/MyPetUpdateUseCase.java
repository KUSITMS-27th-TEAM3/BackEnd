package com.kusitms.samsion.domain.user.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.user.application.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.domain.user.application.mapper.MyPetMapper;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.domain.entity.MyPet;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;

import lombok.RequiredArgsConstructor;

@UserCase
@RequiredArgsConstructor
public class MyPetUpdateUseCase {

	private final UserUtils userUtils;
	private final S3UploadService s3UploadService;

	@Transactional
	public MyPetResponse updateMyPetInfo(final MyPetUpdateRequest request){
		final String imageUrl = s3UploadService.uploadImg(request.getFile());

		User user = userUtils.getUser();
		final MyPet myPet = MyPetMapper.mapToMyPetUpdateRequest(request, imageUrl);
		user.updateMyPet(myPet);
		return MyPetMapper.mapToMyPetResponse(user);
	}

}
