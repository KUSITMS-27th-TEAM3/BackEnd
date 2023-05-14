package com.kusitms.samsion.domain.auth.application.service;

import com.kusitms.samsion.domain.auth.application.dto.ReIssueResponse;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.common.security.jwt.JwtProvider;
import com.kusitms.samsion.common.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@UserCase
@RequiredArgsConstructor
public class AuthReIssueUseCase {

	private final JwtProvider jwtProvider;

	public ReIssueResponse reissue(final String refreshTokenHeader) {
		final String refreshToken = SecurityUtils.validateHeaderAndGetToken(refreshTokenHeader);
		return new ReIssueResponse(jwtProvider.reIssue(refreshToken));
	}

}
