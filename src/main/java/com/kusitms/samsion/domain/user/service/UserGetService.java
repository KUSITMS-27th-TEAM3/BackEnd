package com.kusitms.samsion.domain.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.domain.user.exception.UserNotFoundException;
import com.kusitms.samsion.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserGetService {

	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new UserNotFoundException(Error.USER_NOT_FOUND));
	}
}
