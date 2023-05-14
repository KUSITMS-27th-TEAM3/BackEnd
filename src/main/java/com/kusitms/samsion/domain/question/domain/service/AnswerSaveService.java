package com.kusitms.samsion.domain.question.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.question.domain.entity.Answer;
import com.kusitms.samsion.domain.question.domain.entity.Question;
import com.kusitms.samsion.domain.question.domain.exception.AnswerAlreadyExistException;
import com.kusitms.samsion.domain.question.domain.repository.AnswerRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AnswerSaveService {

	private final AnswerRepository answerRepository;

	public void saveAnswer(String answerDescription, User user, Question question) {
		if(answerRepository.existsByWriterIdAndQuestionId(user.getId(), question.getId())){
			throw new AnswerAlreadyExistException(Error.ANSWER_ALREADY_EXIST);
		}
		final Answer answer = new Answer(answerDescription, user, question);
		answerRepository.save(answer);
	}
}
