package com.kusitms.samsion.domain.question.exception;

import com.kusitms.samsion.common.exception.Error;

public class QuestionNotFoundException extends QuestionException{
	public QuestionNotFoundException(Error error) {
		super(error);
	}
}
