package com.kusitms.samsion.domain.empathy.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class EmpathyNotFoundException extends EmpathyException {
	public EmpathyNotFoundException(Error error) {
		super(error);
	}
}
