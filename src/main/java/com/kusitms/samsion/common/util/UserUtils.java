package com.kusitms.samsion.common.util;

import org.springframework.stereotype.Component;

import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.domain.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUtils {

	private final UserQueryService userQueryService;

	public User getUser(){
		final String userEmail = SecurityUtils.getUserEmail();
		return userQueryService.getUserByEmail(userEmail);
	}
}
