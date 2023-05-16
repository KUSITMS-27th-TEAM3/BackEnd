package com.kusitms.samsion.domain.question.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.question.application.dto.request.AnswerCreateRequest;
import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.domain.entity.Question;
import com.kusitms.samsion.domain.question.domain.service.AnswerSaveService;
import com.kusitms.samsion.domain.question.domain.service.QuestionQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional
@UseCase
@RequiredArgsConstructor
public class AnswerCreateUseCase {

	private final UserUtils userUtils;
	private final AnswerSaveService answerSaveService;
	private final QuestionQueryService questionQueryService;

	public void createAnswer(Long questionId, AnswerCreateRequest answerCreateRequest) {
		final User user = userUtils.getUser();
		final Question question = questionQueryService.getQuestionById(questionId);
		answerSaveService.saveAnswer(answerCreateRequest.getAnswerDescription(), user, question);
	}
}
