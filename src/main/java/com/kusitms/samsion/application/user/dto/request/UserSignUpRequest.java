package com.kusitms.samsion.application.user.dto.request;

import lombok.Getter;

@Getter
public class UserSignUpRequest {

	String email;
	String nickname;

	public UserSignUpRequest(String email, String nickname) {
		this.email = email;
		this.nickname = nickname;
	}
}
