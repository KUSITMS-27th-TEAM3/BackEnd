package com.kusitms.samsion.common.security.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kusitms.samsion.common.consts.ApplicationStatic;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.common.security.exception.TokenNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO : Authorities 설정 변경 필요 (현재는 ROLE_USER로 고정, enum으로 관리 필요)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		final String header = request.getHeader(ApplicationStatic.ACCESS_TOKEN_HEADER);
		validateAuthorizationHeader(header);

		final String accessToken = validateAccessToken(header);
		setAuthentication(accessToken);

		filterChain.doFilter(request, response);
	}

	private void validateAuthorizationHeader(final String header) {
		if (header == null || !header.startsWith(ApplicationStatic.JWT_AUTHORIZATION_TYPE)) {
			throw new TokenNotFoundException(Error.INVALID_TOKEN);
		}
	}

	private String validateAccessToken(final String header) {
		final String accessToken = header.replace(ApplicationStatic.JWT_AUTHORIZATION_TYPE, "");
		jwtProvider.validateToken(accessToken);
		return accessToken;
	}

	private void setAuthentication(final String accessToken){
		final String email = jwtProvider.extractEmail(accessToken);
		List<GrantedAuthority> grantedAuthorities = Collections.singletonList(
			new SimpleGrantedAuthority("ROLE_USER"));
		JwtAuthenticationToken authentication = new JwtAuthenticationToken(grantedAuthorities, email);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
