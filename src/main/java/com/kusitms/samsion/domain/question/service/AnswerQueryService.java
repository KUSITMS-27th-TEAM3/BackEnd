package com.kusitms.samsion.domain.question.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.question.entity.Answer;
import com.kusitms.samsion.domain.question.exception.AnswerNotFoundException;
import com.kusitms.samsion.domain.question.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AnswerQueryService {

	private final AnswerRepository answerRepository;


	public Answer getAnswerByUserIdAndQuestionId(Long userId, Long questionId) {
		return answerRepository.findByWriterIdAndQuestionId(userId, questionId)
			.orElseThrow(() -> new AnswerNotFoundException(Error.ANSWER_NOT_FOUND));
	}

}
