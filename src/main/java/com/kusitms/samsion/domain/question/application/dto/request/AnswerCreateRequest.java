package com.kusitms.samsion.domain.question.application.dto.request;

import lombok.Getter;

@Getter
public class AnswerCreateRequest {

	private String answerDescription;

	public AnswerCreateRequest() {
	}

	public AnswerCreateRequest(String answerDescription) {
		this.answerDescription = answerDescription;
	}
}
