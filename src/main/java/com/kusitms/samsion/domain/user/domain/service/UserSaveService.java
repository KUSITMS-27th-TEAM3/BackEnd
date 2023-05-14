package com.kusitms.samsion.domain.user.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.domain.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserSaveService {

	private final UserRepository userRepository;

	@Transactional
	public void saveUser(User user) {
		if(!userRepository.existsByEmail(user.getEmail())){
			userRepository.save(user);
		}
	}

}
