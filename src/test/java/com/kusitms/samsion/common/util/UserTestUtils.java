package com.kusitms.samsion.common.util;

import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.user.entity.MyPet;
import com.kusitms.samsion.domain.user.entity.User;

public class UserTestUtils {

	public static User getMockUser(){
		User mockUser = User.builder()
			.email(TestConst.TEST_EMAIL)
			.nickname(TestConst.TEST_NICKNAME)
			.build();
		mockUser.updateMyPet(getMockMyPet());
		ReflectionTestUtils.setField(mockUser, "id", TestConst.TEST_ID);

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
