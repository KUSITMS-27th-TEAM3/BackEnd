package com.kusitms.samsion.domain.user.application.handler;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.grid.domain.service.GridSaveService;
import com.kusitms.samsion.domain.user.application.dto.request.UserSignUpRequest;
import com.kusitms.samsion.domain.user.domain.service.UserSaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserSignUpHandler 테스트")
class UserSignUpHandlerTest {

	@Mock
	UserSaveService userSaveService;
	@Mock
	GridSaveService gridSaveService;

	UserSignUpHandler userSignUpHandler;

	@BeforeEach
	void setUp() {
		userSignUpHandler = new UserSignUpHandler(userSaveService, gridSaveService);
	}

	@Test
	void 사용자_가입_요청을_처리한다() {
		//given
		final UserSignUpRequest mockUserSignUpRequest = getMockUserSignUpRequest();
		//when
		userSignUpHandler.signUp(mockUserSignUpRequest);
		//then
		then(userSaveService).should(times(1)).saveUser(any());
	}

	UserSignUpRequest getMockUserSignUpRequest() {
		return UserSignUpRequest.builder()
			.email(TestConst.TEST_EMAIL)
			.nickname(TestConst.TEST_NICKNAME)
			.build();
	}

}