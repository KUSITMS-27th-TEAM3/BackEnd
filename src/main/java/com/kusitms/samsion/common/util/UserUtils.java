package com.kusitms.samsion.common.util;

import org.springframework.stereotype.Component;

import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.domain.user.service.UserGetService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUtils {

	private final UserGetService userGetService;

	public User getUser(){
		final String userEmail = SecurityUtils.getUserEmail();
		return userGetService.getUserByEmail(userEmail);
	}
}
