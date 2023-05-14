package com.kusitms.samsion.domain.question.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionInfoResponse {

	private final Long questionId;
	private final String questionTitle;

	private final String answerDescription;

	@Builder
	public QuestionInfoResponse(Long questionId, String questionTitle, String answerDescription) {
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.answerDescription = answerDescription;
	}
}
