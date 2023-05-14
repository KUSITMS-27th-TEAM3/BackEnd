package com.kusitms.samsion.domain.user.application.handler;

import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.user.application.dto.request.UserSignUpRequest;
import com.kusitms.samsion.domain.user.application.mapper.UserMapper;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.domain.user.domain.service.UserSaveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UserCase
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
