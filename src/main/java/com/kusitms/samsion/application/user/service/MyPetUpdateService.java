package com.kusitms.samsion.application.user.service;

import com.kusitms.samsion.infrastructure.s3.S3UploadService;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.application.user.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.application.user.dto.response.MyPetResponse;
import com.kusitms.samsion.application.user.mapper.MyPetMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.entity.MyPet;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class MyPetUpdateService {

	private final UserUtils userUtils;
	private final MyPetMapper myPetMapper;
	private final S3UploadService s3UploadService;

	@Transactional
	public MyPetResponse updateMyPetInfo(MyPetUpdateRequest request){
		final String imageUrl = s3UploadService.uploadImg(request.getFile());

		User user = userUtils.getUser();
		final MyPet myPet = myPetMapper.mapToMyPetUpdateRequest(request, imageUrl);
		user.updateMyPet(myPet);
		return myPetMapper.mapToMyPetResponse(user);
	}

}
