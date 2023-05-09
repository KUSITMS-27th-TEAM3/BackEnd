package com.kusitms.samsion.domain.empathy.exception;

import com.kusitms.samsion.common.exception.Error;

public class EmpathyNotFoundException extends EmpathyException {
	public EmpathyNotFoundException(Error error) {
		super(error);
	}
}
