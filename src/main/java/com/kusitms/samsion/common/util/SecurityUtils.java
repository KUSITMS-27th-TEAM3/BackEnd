package com.kusitms.samsion.common.util;

import java.util.Optional;

import org.springframework.util.StringUtils;

import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.common.security.exception.TokenNotFoundException;

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

}
