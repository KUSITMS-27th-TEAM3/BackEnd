package com.kusitms.samsion.common.security.oauth;

import static com.kusitms.samsion.common.consts.ApplicationStatic.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kusitms.samsion.common.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

/**
 * TODO : 현제 토큰 생성에서 email 정보만을 담고있음. 추후에 유저 역할(ROLE_USER)등 정보를 추가할지 고민.
 */

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

	private final JwtProvider jwtProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication){
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		generateToken(oAuth2User.getAttribute("email"), response);
	}

	private void generateToken(String email, HttpServletResponse response){
		String accessToken = jwtProvider.generateAccessToken(email);
		String refreshToken = jwtProvider.generateRefreshToken(email);

		response.setHeader(ACCESS_TOKEN_HEADER, JWT_AUTHORIZATION_TYPE+accessToken);
		response.setHeader(REFRESH_TOKEN_HEADER, JWT_AUTHORIZATION_TYPE+refreshToken);
	}
}
