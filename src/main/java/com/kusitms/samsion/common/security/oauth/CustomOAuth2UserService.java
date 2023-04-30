package com.kusitms.samsion.common.security.oauth;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UserRepository userRepository;

	/**
	 * TODO : 코드 리팩터링, 예외처리 추가하기, kakao, naver 로그인 추가하기 , Authorities 설정 변경 필요 (현재는 ROLE_USER로 고정, enum으로 관리 필요)
	 * CustomOAuth2UserService 클래스를 통해서 구글 로그인 이후 가져온 사용자의 정보(email, name, picture)들을 기반으로 가입
	 */
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

		ClientRegistration clientRegistration = userRequest.getClientRegistration();

		String registrationId = clientRegistration.getRegistrationId();
		String userNameAttributeName = clientRegistration.getProviderDetails()
			.getUserInfoEndpoint()
			.getUserNameAttributeName();

		OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

		saveOrUpdate(attributes);

		return new DefaultOAuth2User(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
			, attributes.getAttributes()
			, attributes.getNameAttributeKey());
	}

	private void saveOrUpdate(OAuth2Attributes attributes) {
		User user = userRepository.findByEmail(attributes.getEmail())
			.map(entity -> entity.update(attributes.getName(), attributes.getImageUrl()))
			.orElse(attributes.toEntity());

		userRepository.save(user);
	}
}
