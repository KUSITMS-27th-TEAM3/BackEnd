package com.kusitms.samsion.application.question.service;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.application.question.dto.request.AnswerUpdateRequest;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.QuestionTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.question.entity.Answer;
import com.kusitms.samsion.domain.question.service.AnswerQueryService;
import com.kusitms.samsion.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("AnswerUpdateService 테스트")
class AnswerUpdateServiceTest {

	@Mock
	UserUtils userUtils;
	@Mock
	AnswerQueryService answerQueryService;

	AnswerUpdateService answerUpdateService;

	@BeforeEach
	void setUp(){
		answerUpdateService = new AnswerUpdateService(userUtils, answerQueryService);
	}

	@Test
	void 답변을_수정한다(){
		//given
		User mockUser = UserTestUtils.getMockUser();
		Answer mockAnswer = QuestionTestUtils.getMockAnswer();
		AnswerUpdateRequest answerUpdateRequest = new AnswerUpdateRequest(TestConst.TEST_UPDATE_DESCRIPTION);
		given(userUtils.getUser()).willReturn(mockUser);
		given(answerQueryService.getAnswerByUserIdAndQuestionId(TestConst.TEST_USER_ID, TestConst.TEST_QUESTION_ID)).willReturn(mockAnswer);
		//when
		answerUpdateService.updateAnswer(TestConst.TEST_QUESTION_ID, answerUpdateRequest);
		//then
		Assertions.assertThat(mockAnswer.getDescription()).isEqualTo(TestConst.TEST_UPDATE_DESCRIPTION);
	}


}