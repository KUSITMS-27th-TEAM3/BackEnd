package com.kusitms.samsion.common.security.oauth;

import static com.kusitms.samsion.common.consts.ApplicationConst.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kusitms.samsion.common.security.jwt.JwtProvider;
import com.kusitms.samsion.common.util.HeaderUtils;

import lombok.Getter;
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
		TokenInfo tokenInfo = generateToken(oAuth2User.getAttribute("email"));
		setTokenToHeader(tokenInfo);
	}

	private TokenInfo generateToken(String email){
		String accessToken = jwtProvider.generateAccessToken(email);
		String refreshToken = jwtProvider.generateRefreshToken(email);

		return new TokenInfo(accessToken, refreshToken);
	}

	private void setTokenToHeader(TokenInfo tokenInfo){
		HeaderUtils.setHeader(ACCESS_TOKEN_HEADER, JWT_AUTHORIZATION_TYPE+tokenInfo.getAccessToken());
		HeaderUtils.setHeader(REFRESH_TOKEN_HEADER, JWT_AUTHORIZATION_TYPE+tokenInfo.getRefreshToken());
	}

	@Getter
	private static class TokenInfo{
		private final String accessToken;
		private final String refreshToken;

		public TokenInfo(String accessToken, String refreshToken) {
			this.accessToken = accessToken;
			this.refreshToken = refreshToken;
		}

	}
}
