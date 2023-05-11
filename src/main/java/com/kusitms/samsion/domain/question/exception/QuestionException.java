package com.kusitms.samsion.domain.question.exception;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.Error;

public class QuestionException extends BusinessException {
	public QuestionException(Error error) {
		super(error);
	}
}
