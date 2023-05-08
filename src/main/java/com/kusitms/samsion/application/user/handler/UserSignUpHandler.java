package com.kusitms.samsion.application.user.handler;

import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.application.user.dto.request.UserSignUpRequest;
import com.kusitms.samsion.application.user.mapper.UserMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.domain.user.service.UserSaveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationService
@RequiredArgsConstructor
public class UserSignUpHandler {

	private final UserSaveService userSaveService;

	@Transactional
	@EventListener
	public void signUp(UserSignUpRequest request){
		log.info("request {}", request);
		final User user = UserMapper.toEntity(request);
		userSaveService.saveUser(user);
	}
}
