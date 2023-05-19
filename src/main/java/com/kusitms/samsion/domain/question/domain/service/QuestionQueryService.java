package com.kusitms.samsion.domain.question.domain.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.question.domain.entity.Question;
import com.kusitms.samsion.domain.question.domain.exception.QuestionNotFoundException;
import com.kusitms.samsion.domain.question.domain.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class QuestionQueryService {

	private final QuestionRepository questionRepository;

	public Slice<Question> findAll(Pageable pageable, Long userId){
		return questionRepository.findAllBy(pageable);
		// return questionRepository.findAll(pageable,userId);
	}

	public Question getQuestionById(Long questionId){
		return questionRepository.findById(questionId)
			.orElseThrow(() -> new QuestionNotFoundException(Error.QUESTION_NOT_FOUND));
	}

}
