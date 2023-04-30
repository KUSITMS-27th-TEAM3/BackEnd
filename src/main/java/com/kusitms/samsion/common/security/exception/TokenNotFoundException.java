package com.kusitms.samsion.common.security.exception;

import com.kusitms.samsion.common.exception.Error;

public class TokenNotFoundException extends JwtException {
	public TokenNotFoundException(Error error) {
		super(error);
	}
}
