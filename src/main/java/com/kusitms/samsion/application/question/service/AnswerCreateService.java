package com.kusitms.samsion.application.question.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.application.question.dto.request.AnswerCreateRequest;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.entity.Question;
import com.kusitms.samsion.domain.question.service.AnswerSaveService;
import com.kusitms.samsion.domain.question.service.QuestionQueryService;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional
@ApplicationService
@RequiredArgsConstructor
public class AnswerCreateService {

	private final UserUtils userUtils;
	private final AnswerSaveService answerSaveService;
	private final QuestionQueryService questionQueryService;

	public void createAnswer(Long questionId, AnswerCreateRequest answerCreateRequest) {
		final User user = userUtils.getUser();
		final Question question = questionQueryService.getQuestionById(questionId);
		answerSaveService.saveAnswer(answerCreateRequest.getAnswerDescription(), user, question);
	}
}
