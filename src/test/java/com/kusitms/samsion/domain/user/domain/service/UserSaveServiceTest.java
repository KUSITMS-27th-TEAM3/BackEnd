package com.kusitms.samsion.domain.user.domain.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.domain.user.domain.repository.UserRepository;
import com.kusitms.samsion.domain.user.domain.service.UserSaveService;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserSaveService 테스트")
class UserSaveServiceTest {

	@Mock
	UserRepository userRepository;

	UserSaveService userSaveService;

	@BeforeEach
	void setUp() {
		userSaveService = new UserSaveService(userRepository);
	}

	@Test
	void 새로운_사용자를_저장한다() {
		//given
		User mockUser = UserTestUtils.getMockUser();
		given(userRepository.existsByEmail(mockUser.getEmail())).willReturn(false);
		//when
		userSaveService.saveUser(mockUser);
		//then
		then(userRepository).should(times(1)).save(mockUser);
	}

	@Test
	void 이미_존재하는_사용자를_저장하려고_하는_경우_저장하지_않는다() {
		//given
		User mockUser = UserTestUtils.getMockUser();
		given(userRepository.existsByEmail(mockUser.getEmail())).willReturn(true);
		//when
		userSaveService.saveUser(mockUser);
		//then
		then(userRepository).should(times(0)).save(mockUser);
	}


}