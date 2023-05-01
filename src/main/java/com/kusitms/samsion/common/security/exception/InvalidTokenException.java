package com.kusitms.samsion.common.security.exception;

import com.kusitms.samsion.common.exception.Error;

public class InvalidTokenException extends JwtException{

	public InvalidTokenException(Error error) {
		super(error);
	}
}
