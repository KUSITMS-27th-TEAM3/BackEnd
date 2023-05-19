package com.kusitms.samsion.domain.question.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.application.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.domain.question.application.mapper.QuestionMapper;
import com.kusitms.samsion.domain.question.domain.entity.Answer;
import com.kusitms.samsion.domain.question.domain.entity.Question;
import com.kusitms.samsion.domain.question.domain.exception.QuestionException;
import com.kusitms.samsion.domain.question.domain.service.AnswerQueryService;
import com.kusitms.samsion.domain.question.domain.service.QuestionQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@UseCase
@RequiredArgsConstructor
public class QuestionReadUseCase {

	private final UserUtils userUtils;
	private final QuestionQueryService questionQueryService;
	private final AnswerQueryService answerQueryService;

	/**
	 * jpa exist 메서드을 사용하고 find 메서드 호출시 select 쿼리가 2개 발생하는 문제가 있어서 예외를 통한 존재여부 체크함
	 */
	public SliceResponse<QuestionInfoResponse> getQuestionList(Pageable pageable) {
		User user = userUtils.getUser();
		Slice<Question> questionList = questionQueryService.findAll(pageable, user.getId());
		Slice<QuestionInfoResponse> questionInfoResponses = questionList.map(question -> {
			try {
				Answer answer = answerQueryService.getAnswerByUserIdAndQuestionId(user.getId(), question.getId());
				return QuestionMapper.toQuestionInfoResponseWithAnswer(question, answer);
			} catch (QuestionException e) {
				return QuestionMapper.toQuestionInfoResponse(question);
			}
		});
		return SliceResponse.of(questionInfoResponses);
	}

}
