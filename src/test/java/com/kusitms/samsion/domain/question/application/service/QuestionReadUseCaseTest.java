package com.kusitms.samsion.domain.question.application.service;

import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kusitms.samsion.domain.question.application.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.common.slice.PageResponse;
import com.kusitms.samsion.common.util.QuestionTestUtils;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.application.service.QuestionReadUseCase;
import com.kusitms.samsion.domain.question.domain.entity.Answer;
import com.kusitms.samsion.domain.question.domain.entity.Question;
import com.kusitms.samsion.domain.question.domain.exception.AnswerNotFoundException;
import com.kusitms.samsion.domain.question.domain.service.AnswerQueryService;
import com.kusitms.samsion.domain.question.domain.service.QuestionQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("QuestionReadService 테스트")
class QuestionReadUseCaseTest {

	@Mock
	UserUtils userUtils;
	@Mock
	QuestionQueryService questionQueryService;
	@Mock
	AnswerQueryService answerQueryService;

	QuestionReadUseCase questionReadUseCase;

	@BeforeEach
	void setUp(){
		questionReadUseCase = new QuestionReadUseCase(userUtils, questionQueryService, answerQueryService);
	}

	@Nested
	class getQuestionList_메서드에서{
		@Test
		void 모든_질문에_답변이_존재한다(){
			//given
			User mockUser = UserTestUtils.getMockUser();
			Question mockQuestion = QuestionTestUtils.getMockQuestion();
			List<Question> mockQuestionList = List.of(mockQuestion);
			Pageable mockPageable = SliceTestUtils.getMockPageable();
			Page<Question> mockPage = SliceTestUtils.getMockPage(mockQuestionList);
			Answer mockAnswer = QuestionTestUtils.getMockAnswer();
			given(userUtils.getUser()).willReturn(mockUser);
			given(questionQueryService.findAll(mockPageable, mockUser.getId())).willReturn(mockPage);
			given(answerQueryService.getAnswerByUserIdAndQuestionId(mockUser.getId(), mockQuestion.getId())).willReturn(mockAnswer);
			//when
			PageResponse<QuestionInfoResponse> questionList = questionReadUseCase.getQuestionList(mockPageable);
			//then
			QuestionInfoResponse questionInfoResponse = questionList.getContent().get(0);
			Assertions.assertThat(questionInfoResponse.getQuestionId()).isEqualTo(mockQuestion.getId());
			Assertions.assertThat(questionInfoResponse.getQuestionTitle()).isEqualTo(mockQuestion.getTitle());
			Assertions.assertThat(questionInfoResponse.getAnswerDescription()).isEqualTo(mockAnswer.getDescription());
		}

		@Test
		void 질문에_대해서_답변이_존재하지_않으면_null을_반환한다(){
			//given
			User mockUser = UserTestUtils.getMockUser();
			Question mockQuestion = QuestionTestUtils.getMockQuestion();
			List<Question> mockQuestionList = List.of(mockQuestion);
			Pageable mockPageable = SliceTestUtils.getMockPageable();
			Page<Question> mockPage = SliceTestUtils.getMockPage(mockQuestionList);
			given(userUtils.getUser()).willReturn(mockUser);
			given(questionQueryService.findAll(mockPageable, mockUser.getId())).willReturn(mockPage);
			given(answerQueryService.getAnswerByUserIdAndQuestionId(mockUser.getId(), mockQuestion.getId())).willThrow(new AnswerNotFoundException(
				Error.ANSWER_NOT_FOUND));
			//when
			PageResponse<QuestionInfoResponse> questionList = questionReadUseCase.getQuestionList(mockPageable);
			//then
			QuestionInfoResponse questionInfoResponse = questionList.getContent().get(0);
			Assertions.assertThat(questionInfoResponse.getQuestionId()).isEqualTo(mockQuestion.getId());
			Assertions.assertThat(questionInfoResponse.getQuestionTitle()).isEqualTo(mockQuestion.getTitle());
			Assertions.assertThat(questionInfoResponse.getAnswerDescription()).isNull();
		}
	}

}