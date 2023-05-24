package com.kusitms.samsion.domain.user.application.handler;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.domain.grid.application.dto.request.GridCreateRequest;
import com.kusitms.samsion.domain.user.application.dto.request.UserSignUpRequest;
import com.kusitms.samsion.domain.user.application.mapper.UserMapper;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.domain.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class UserSignUpHandler {

	private final UserSaveService userSaveService;

	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	@EventListener
	public void signUp(UserSignUpRequest request){
		final User user = UserMapper.toEntity(request);
		userSaveService.saveUser(user);
		userSaveService.flush();
		applicationEventPublisher.publishEvent(new GridCreateRequest(user.getId()));
	}

}
