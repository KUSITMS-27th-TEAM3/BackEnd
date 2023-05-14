package com.kusitms.samsion.domain.user.domain.exception;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.Error;

public class UserException extends BusinessException {

	public UserException(Error error) {
		super(error);
	}
}
