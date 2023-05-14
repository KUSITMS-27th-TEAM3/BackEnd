package com.kusitms.samsion.domain.question.application.mapper;

import com.kusitms.samsion.domain.question.application.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.domain.question.domain.entity.Answer;
import com.kusitms.samsion.domain.question.domain.entity.Question;

public class QuestionMapper {

	public static QuestionInfoResponse toQuestionInfoResponseWithAnswer(Question question, Answer answer) {
		return QuestionInfoResponse.builder()
				.questionId(question.getId())
				.questionTitle(question.getTitle())
				.answerDescription(answer.getDescription())
				.build();
	}

	public static QuestionInfoResponse toQuestionInfoResponse(Question question) {
		return QuestionInfoResponse.builder()
				.questionId(question.getId())
				.questionTitle(question.getTitle())
				.build();
	}
}
