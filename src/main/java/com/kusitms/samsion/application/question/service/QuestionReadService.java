package com.kusitms.samsion.application.question.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.application.question.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.application.question.mapper.QuestionMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.slice.PageResponse;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.entity.Answer;
import com.kusitms.samsion.domain.question.entity.Question;
import com.kusitms.samsion.domain.question.exception.QuestionException;
import com.kusitms.samsion.domain.question.service.AnswerQueryService;
import com.kusitms.samsion.domain.question.service.QuestionQueryService;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@ApplicationService
@RequiredArgsConstructor
public class QuestionReadService {

	private final UserUtils userUtils;
	private final QuestionQueryService questionQueryService;
	private final AnswerQueryService answerQueryService;

	/**
	 * jpa exist 메서드을 사용하고 find 메서드 호출시 select 쿼리가 2개 발생하는 문제가 있어서 예외를 통한 존재여부 체크함
	 */
	public PageResponse<QuestionInfoResponse> getQuestionList(Pageable pageable) {
		User user = userUtils.getUser();
		Page<Question> questionList = questionQueryService.findAll(pageable, user.getId());
		Page<QuestionInfoResponse> questionInfoResponses = questionList.map(question -> {
			try {
				Answer answer = answerQueryService.getAnswerByUserIdAndQuestionId(user.getId(), question.getId());
				return QuestionMapper.toQuestionInfoResponseWithAnswer(question, answer);
			} catch (QuestionException e) {
				return QuestionMapper.toQuestionInfoResponse(question);
			}
		});
		return PageResponse.of(questionInfoResponses);
	}

}
