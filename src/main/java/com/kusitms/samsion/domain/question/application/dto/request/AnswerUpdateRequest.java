package com.kusitms.samsion.domain.question.application.dto.request;

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
