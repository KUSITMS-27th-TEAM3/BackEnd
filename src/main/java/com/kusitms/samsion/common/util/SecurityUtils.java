package com.kusitms.samsion.common.util;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.common.security.exception.TokenNotFoundException;
import com.kusitms.samsion.common.security.jwt.JwtAuthenticationToken;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SecurityUtils {

	public static String validateHeaderAndGetToken(final String headerValue){
		return Optional.ofNullable(headerValue)
			.filter(header -> header.startsWith(ApplicationConst.JWT_AUTHORIZATION_TYPE))
			.filter(StringUtils::hasText)
			.map(header -> header.replace(ApplicationConst.JWT_AUTHORIZATION_TYPE, ""))
			.orElseThrow(() -> new TokenNotFoundException(Error.INVALID_TOKEN));
	}

	public static String getUserEmail(){
		return (String)getAuthentication().getPrincipal();
	}

	private static JwtAuthenticationToken getAuthentication(){
		return (JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
	}

}
