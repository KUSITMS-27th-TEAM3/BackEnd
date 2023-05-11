package com.kusitms.samsion.application.question.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.application.question.dto.request.AnswerUpdateRequest;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.entity.Answer;
import com.kusitms.samsion.domain.question.service.AnswerQueryService;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional
@ApplicationService
@RequiredArgsConstructor
public class AnswerUpdateService {

	private final UserUtils userUtils;
	private final AnswerQueryService answerQueryService;

	public void updateAnswer(Long questionId, AnswerUpdateRequest answerUpdateRequest) {
		User user = userUtils.getUser();
		Answer answer = answerQueryService.getAnswerByUserIdAndQuestionId(user.getId(), questionId);
		answer.updateAnswer(answerUpdateRequest.getAnswerDescription());
	}
}
