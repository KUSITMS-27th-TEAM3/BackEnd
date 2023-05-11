package com.kusitms.samsion.application.question.mapper;

import com.kusitms.samsion.application.question.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.domain.question.entity.Answer;
import com.kusitms.samsion.domain.question.entity.Question;

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
