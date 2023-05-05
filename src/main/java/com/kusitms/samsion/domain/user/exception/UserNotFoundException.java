package com.kusitms.samsion.domain.user.exception;

import com.kusitms.samsion.common.exception.Error;

public class UserNotFoundException extends UserException{
	public UserNotFoundException(Error error) {
		super(error);
	}
}
