package com.kusitms.samsion.application.question.dto.request;

import lombok.Getter;

@Getter
public class AnswerUpdateRequest {

	private String answerDescription;

	public AnswerUpdateRequest() {
	}

	public AnswerUpdateRequest(String answerDescription) {
		this.answerDescription = answerDescription;
	}
}
