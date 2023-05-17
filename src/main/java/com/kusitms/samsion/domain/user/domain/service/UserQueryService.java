package com.kusitms.samsion.domain.user.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.domain.user.domain.exception.UserNotFoundException;
import com.kusitms.samsion.domain.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {

	private final UserRepository userRepository;


	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new UserNotFoundException(Error.USER_NOT_FOUND));
	}

	public User findById(Long userId){
		return userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException(Error.USER_NOT_FOUND));
	}


}
