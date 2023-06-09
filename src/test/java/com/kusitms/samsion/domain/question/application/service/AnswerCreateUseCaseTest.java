package com.kusitms.samsion.domain.question.application.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.QuestionTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.application.dto.request.AnswerCreateRequest;
import com.kusitms.samsion.domain.question.domain.entity.Question;
import com.kusitms.samsion.domain.question.domain.service.AnswerSaveService;
import com.kusitms.samsion.domain.question.domain.service.QuestionQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("AnswerCreateUseCase 테스트")
class AnswerCreateUseCaseTest {

	@Mock
	UserUtils userUtils;
	@Mock
	AnswerSaveService answerSaveService;
	@Mock
	QuestionQueryService questionQueryService;

	AnswerCreateUseCase answerCreateUseCase;

	@BeforeEach
	void setUp(){
		answerCreateUseCase = new AnswerCreateUseCase(userUtils, answerSaveService, questionQueryService);
	}

	@Test
	void 답변을_생성한다(){
		//given
		User mockUser = UserTestUtils.getMockUser();
		Question mockQuestion = QuestionTestUtils.getMockQuestion();
		given(userUtils.getUser()).willReturn(mockUser);
		given(questionQueryService.getQuestionById(mockQuestion.getId())).willReturn(mockQuestion);
		AnswerCreateRequest answerCreateRequest = new AnswerCreateRequest(TestConst.TEST_ANSWER_DESCRIPTION);
		//when
		answerCreateUseCase.createAnswer(mockQuestion.getId(), answerCreateRequest);
		//then
		then(answerSaveService).should(times(1)).saveAnswer(TestConst.TEST_ANSWER_DESCRIPTION, mockUser, mockQuestion);
	}

}