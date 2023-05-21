package com.kusitms.samsion.domain.user.application.service;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.domain.user.domain.entity.MyPet;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("MyPetInfoUseCase 테스트")
class MyPetInfoUseCaseTest {

	@Mock
	UserUtils userUtils;

	MyPetInfoUseCase myPetInfoUseCase;

	@BeforeEach
	void setUp() {
		myPetInfoUseCase = new MyPetInfoUseCase(userUtils);
	}

	@Test
	void 현재_사용자의_반려동물_정보를_가져온다() {
		//given
		final User mockUser = UserTestUtils.getMockUser();
		final MyPet mockMyPet = mockUser.getMypet();
		given(userUtils.getUser()).willReturn(mockUser);
		//when
		MyPetResponse myPetInfo = myPetInfoUseCase.getMyPetInfo();
		//then
		Assertions.assertThat(myPetInfo).isNotNull();
		Assertions.assertThat(myPetInfo.getPetName()).isEqualTo(mockMyPet.getPetName());
		Assertions.assertThat(myPetInfo.getUserNickname()).isEqualTo(mockUser.getNickname());
		Assertions.assertThat(myPetInfo.getDescription()).isEqualTo(mockMyPet.getDescription());
		Assertions.assertThat(myPetInfo.getProfileImageUrl()).isEqualTo(mockMyPet.getPetImageUrl());
		Assertions.assertThat(myPetInfo.getPetType()).isEqualTo(mockMyPet.getPetType());
		Assertions.assertThat(myPetInfo.getPetAge()).isEqualTo(mockMyPet.getPetAge());
	}
}