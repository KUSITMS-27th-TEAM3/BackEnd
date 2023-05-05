package com.kusitms.samsion.application.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.application.user.dto.response.MyPetResponse;
import com.kusitms.samsion.application.user.mapper.MyPetMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPetInfoService {

	private final UserUtils userUtils;
	private final MyPetMapper myPetMapper;

	public MyPetResponse getMyPetInfo(){
		User user = userUtils.getUser();
		return myPetMapper.getMyPetInfo(user);
	}
}
