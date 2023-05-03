package com.kusitms.samsion.application.auth.service;

import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.security.jwt.JwtProvider;
import com.kusitms.samsion.common.util.HeaderUtils;
import com.kusitms.samsion.common.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class AuthReIssueService {

	private final JwtProvider jwtProvider;

	public void reissue() {
		final String refreshTokenHeader = HeaderUtils.getHeader(ApplicationConst.REFRESH_TOKEN_HEADER);
		final String refreshToken = SecurityUtils.validateHeaderAndGetToken(refreshTokenHeader);
		final String accessToken = jwtProvider.reIssue(refreshToken);
		HeaderUtils.setHeader(ApplicationConst.ACCESS_TOKEN_HEADER, ApplicationConst.JWT_AUTHORIZATION_TYPE + accessToken);
	}

}
