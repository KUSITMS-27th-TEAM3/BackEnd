package com.kusitms.samsion.domain.question.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.question.application.dto.request.AnswerUpdateRequest;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.domain.entity.Answer;
import com.kusitms.samsion.domain.question.domain.service.AnswerQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional
@UserCase
@RequiredArgsConstructor
public class AnswerUpdateUseCase {

	private final UserUtils userUtils;
	private final AnswerQueryService answerQueryService;

	public void updateAnswer(Long questionId, AnswerUpdateRequest answerUpdateRequest) {
		User user = userUtils.getUser();
		Answer answer = answerQueryService.getAnswerByUserIdAndQuestionId(user.getId(), questionId);
		answer.updateAnswer(answerUpdateRequest.getAnswerDescription());
	}
}
