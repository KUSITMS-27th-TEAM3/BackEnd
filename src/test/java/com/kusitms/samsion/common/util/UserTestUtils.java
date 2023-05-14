package com.kusitms.samsion.common.util;

import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.user.domain.entity.MyPet;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTestUtils {

	private final static User mockUser = setMockUserSingleton();

	private static User setMockUserSingleton(){
		User mockUser = User.builder()
			.email(TestConst.TEST_EMAIL)
			.nickname(TestConst.TEST_NICKNAME)
			.build();
		mockUser.updateMyPet(getMockMyPet());
		ReflectionTestUtils.setField(mockUser, "id", TestConst.TEST_USER_ID);

		return mockUser;
	}

	public static User getMockUser(){
		return mockUser;
	}

	private static MyPet getMockMyPet(){
		return MyPet.builder()
			.petName(TestConst.TEST_PET_NAME)
			.petImageUrl(TestConst.TEST_PET_IMAGE_URL)
			.description(TestConst.TEST_DESCRIPTION)
			.build();
	}
}
