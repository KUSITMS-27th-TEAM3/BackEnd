package com.kusitms.samsion.common.security.oauth;

import java.util.Collections;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kusitms.samsion.domain.user.application.dto.request.UserSignUpRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final ApplicationEventPublisher eventPublisher;

	/**
	 * TODO : 코드 리팩터링, 예외처리 추가하기, kakao, naver 로그인 추가하기 , Authorities 설정 변경 필요 (현재는 ROLE_USER로 고정, enum으로 관리 필요)
	 * CustomOAuth2UserService 클래스를 통해서 구글 로그인 이후 가져온 사용자의 정보(email, name, picture)들을 기반으로 가입
	 */
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		final DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
		final OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

		final ClientRegistration clientRegistration = userRequest.getClientRegistration();
		final String registrationId = clientRegistration.getRegistrationId();
		final String userNameAttributeName = getUserNameAttributeName(clientRegistration);

		OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

		saveOAuth2User(attributes);

		return new DefaultOAuth2User(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
			attributes.getAttributes(),
			attributes.getNameAttributeKey());
	}

	/**
	 * 사용자 이름, imageUrl의 경우 사용자가 변경하기 때문에 첫 접속시에만 저장하도록 함, update는 제공하지 않음
	 * 이밴트 기반으로 바꿀 예정
	 */
	private void saveOAuth2User(OAuth2Attributes attributes) {
		eventPublisher.publishEvent(new UserSignUpRequest(attributes.getEmail(), attributes.getName()));
	}

	private String getUserNameAttributeName(ClientRegistration clientRegistration) {
		return clientRegistration.getProviderDetails()
			.getUserInfoEndpoint()
			.getUserNameAttributeName();
	}
}
