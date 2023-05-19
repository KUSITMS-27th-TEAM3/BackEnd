package com.kusitms.samsion.domain.user.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.user.application.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.domain.user.application.mapper.MyPetMapper;
import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.domain.entity.MyPet;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class MyPetUpdateUseCase {

	private final UserUtils userUtils;
	private final S3UploadService s3UploadService;

	@Transactional
	public MyPetResponse updateMyPetInfo(final MyPetUpdateRequest request){
		final String profileImageUrl = s3UploadService.uploadImg(request.getProfileImage());
		final String petImageUrl = s3UploadService.uploadImg(request.getPetImage());
		User user = userUtils.getUser();
		user.updateUserInfo(profileImageUrl);
		final MyPet myPet = MyPetMapper.mapToMyPetUpdateRequest(request, petImageUrl);
		user.updateMyPet(myPet);

		return MyPetMapper.mapToMyPetResponse(user);
	}

}
