package com.kusitms.samsion.application.user.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

	@Transactional
	public MyPetResponse updateMyPetInfo(MultipartFile multipartFile, MyPetUpdateRequest request){
		// TODO : 이미지 업로드
		final String imageUrl = null;

		User user = userUtils.getUser();
		final MyPet myPet = myPetMapper.mapToMyPetUpdateRequest(request, imageUrl);
		user.updateMyPet(myPet);
		return myPetMapper.mapToMyPetResponse(user);
	}

}
