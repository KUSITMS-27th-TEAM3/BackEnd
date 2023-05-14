package com.kusitms.samsion.domain.user.application.mapper;

import com.kusitms.samsion.domain.user.application.dto.request.UserSignUpRequest;
import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.user.domain.entity.User;

@Mapper
public class UserMapper {

	public static User toEntity(UserSignUpRequest request){
		return User.builder()
			.email(request.getEmail())
			.nickname(request.getNickname())
			.build();
	}

}
