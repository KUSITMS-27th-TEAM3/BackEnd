package com.kusitms.samsion.domain.user.service;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.domain.user.exception.UserNotFoundException;
import com.kusitms.samsion.domain.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserGetService 테스트")
class UserGetServiceTest {

	@Mock
	private UserRepository userRepository;

	private UserGetService userGetService;

	@BeforeEach
	void setUp() {
		userGetService = new UserGetService(userRepository);
	}

	@Test
	void email을_통해_사용자정보를_가져온다() {
		//given
		User mockUser = UserTestUtils.getMockUser();
		given(userRepository.findByEmail(TestConst.TEST_EMAIL)).willReturn(Optional.of(mockUser));
		//when
		User user = userGetService.getUserByEmail(mockUser.getEmail());
		//then
		Assertions.assertThat(user).isNotNull();
		Assertions.assertThat(user).usingRecursiveComparison().isEqualTo(mockUser);
	}

	@Test
	void email을_통해_사용자정보를_가져올_수_없는_경우_예외가_발생한다() {
		//given
		given(userRepository.findByEmail(TestConst.TEST_EMAIL)).willReturn(Optional.empty());
		//when
		//then
		Assertions.assertThatThrownBy(() -> userGetService.getUserByEmail(TestConst.TEST_EMAIL))
			.isInstanceOf(UserNotFoundException.class);
	}

}