package com.kusitms.samsion.common.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConst {
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
	public static final String TOKEN_TYPE = "TOKEN_TYPE";

	public static final String JWT_AUTHORIZATION_TYPE = "Bearer"+" ";

	public static final String ACCESS_TOKEN_HEADER = "Authorization";
	public static final String REFRESH_TOKEN_HEADER = "RefreshToken";
}
