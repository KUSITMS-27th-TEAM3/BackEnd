package com.kusitms.samsion.common.security.exception;

import com.kusitms.samsion.common.exception.Error;

public class ExpiredTokenException extends JwtException{
	public ExpiredTokenException(Error error) {
		super(error);
	}
}
