package com.kusitms.samsion.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kusitms.samsion.common.security.jwt.JwtAuthenticationEntryPoint;
import com.kusitms.samsion.common.security.jwt.JwtAuthenticationFilter;
import com.kusitms.samsion.common.security.oauth.CustomOAuth2UserService;
import com.kusitms.samsion.common.security.oauth.OAuth2SuccessHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	/**
	 * TODO : OAuth2FailureHandler 추가하기
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.oauth2Login(
				oauth2Login -> oauth2Login
					.successHandler(oAuth2SuccessHandler)
					.userInfoEndpoint().userService(customOAuth2UserService)
		);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().anyRequest().authenticated();
		http.cors();

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtAuthenticationEntryPoint, JwtAuthenticationFilter.class);
		return http.build();
	}


}
