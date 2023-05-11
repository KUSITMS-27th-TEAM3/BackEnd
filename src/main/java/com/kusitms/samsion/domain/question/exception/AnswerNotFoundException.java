package com.kusitms.samsion.domain.question.exception;

import com.kusitms.samsion.common.exception.Error;

public class AnswerNotFoundException extends QuestionException{
	public AnswerNotFoundException(Error error) {
		super(error);
	}
}
