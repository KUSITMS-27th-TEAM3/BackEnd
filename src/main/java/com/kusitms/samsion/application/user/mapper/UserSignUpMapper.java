package com.kusitms.samsion.application.user.mapper;

import com.kusitms.samsion.application.user.dto.request.UserSignUpRequest;
import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.user.entity.User;

@Mapper
public class UserSignUpMapper {

	public User toEntity(UserSignUpRequest request){
		return User.builder()
			.email(request.getEmail())
			.nickname(request.getNickname())
			.build();
	}


}
