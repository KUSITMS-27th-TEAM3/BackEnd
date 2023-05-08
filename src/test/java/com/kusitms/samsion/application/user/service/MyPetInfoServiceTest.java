package com.kusitms.samsion.application.user.service;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.application.user.dto.response.MyPetResponse;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.entity.MyPet;
import com.kusitms.samsion.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("MyPetInfoService 테스트")
class MyPetInfoServiceTest {

	@Mock
	UserUtils userUtils;

	MyPetInfoService myPetInfoService;

	@BeforeEach
	void setUp() {
		myPetInfoService = new MyPetInfoService(userUtils);
	}

	@Test
	void 현재_사용자의_반려동물_정보를_가져온다() {
		//given
		final User mockUser = UserTestUtils.getMockUser();
		final MyPet mockMyPet = mockUser.getMypet();
		given(userUtils.getUser()).willReturn(mockUser);
		//when
		MyPetResponse myPetInfo = myPetInfoService.getMyPetInfo();
		//then
		Assertions.assertThat(myPetInfo).isNotNull();
		Assertions.assertThat(myPetInfo.getPetName()).isEqualTo(mockMyPet.getPetName());
		Assertions.assertThat(myPetInfo.getDescription()).isEqualTo(mockMyPet.getDescription());
		Assertions.assertThat(myPetInfo.getPetImageUrl()).isEqualTo(mockMyPet.getPetImageUrl());
	}
}