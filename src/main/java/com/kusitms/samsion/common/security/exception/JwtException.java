package com.kusitms.samsion.common.security.exception;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.Error;

public class JwtException extends BusinessException {
	public JwtException(Error error) {
		super(error);
	}
}
