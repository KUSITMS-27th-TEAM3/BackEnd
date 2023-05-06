package com.kusitms.samsion.application.auth.service;

import com.kusitms.samsion.application.auth.dto.ReIssueResponse;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.security.jwt.JwtProvider;
import com.kusitms.samsion.common.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class AuthReIssueService {

	private final JwtProvider jwtProvider;

	public ReIssueResponse reissue(final String refreshTokenHeader) {
		final String refreshToken = SecurityUtils.validateHeaderAndGetToken(refreshTokenHeader);
		return new ReIssueResponse(jwtProvider.reIssue(refreshToken));
	}

}
