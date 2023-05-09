package com.kusitms.samsion.domain.empathy.exception;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.Error;

public class EmpathyException extends BusinessException {
	public EmpathyException(Error error) {
		super(error);
	}
}
