package com.kusitms.samsion.domain.user.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.domain.user.application.mapper.MyPetMapper;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UserCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPetInfoUseCase {

	private final UserUtils userUtils;

	public MyPetResponse getMyPetInfo(){
		User user = userUtils.getUser();
		return MyPetMapper.mapToMyPetResponse(user);
	}
}
