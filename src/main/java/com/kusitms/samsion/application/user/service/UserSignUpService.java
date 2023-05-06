package com.kusitms.samsion.application.user.service;

import org.springframework.context.event.EventListener;

import com.kusitms.samsion.application.user.dto.request.UserSignUpRequest;
import com.kusitms.samsion.application.user.mapper.UserMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.domain.user.service.UserSaveService;

import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class UserSignUpService {

	private final UserMapper userMapper;
	private final UserSaveService userSaveService;

	@EventListener
	public void signUp(UserSignUpRequest request){
		User user = userMapper.toEntity(request);
		userSaveService.saveUser(user);
	}
}
