package com.kusitms.samsion.domain.user.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSignUpRequest {

	private final String email;
	private final String nickname;

	@Builder
	public UserSignUpRequest(String email, String nickname) {
		this.email = email;
		this.nickname = nickname;
	}
}
