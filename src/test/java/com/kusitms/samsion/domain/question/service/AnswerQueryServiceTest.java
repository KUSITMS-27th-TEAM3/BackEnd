package com.kusitms.samsion.domain.question.service;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.QuestionTestUtils;
import com.kusitms.samsion.domain.question.entity.Answer;
import com.kusitms.samsion.domain.question.exception.AnswerNotFoundException;
import com.kusitms.samsion.domain.question.repository.AnswerRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("AnswerQueryService 테스트")
class AnswerQueryServiceTest {

	@Mock
	AnswerRepository answerRepository;

	AnswerQueryService answerQueryService;

	@BeforeEach
	void setUp() {
		answerQueryService = new AnswerQueryService(answerRepository);
	}

	@Nested
	class getAnswerByUserIdAndQuestionId_메서드에서 {

		@Test
		void 답변이_존재하면_반환한다() {
			//given
			Answer mockAnswer = QuestionTestUtils.getMockAnswer();
			given(answerRepository.findByWriterIdAndQuestionId(TestConst.TEST_USER_ID, TestConst.TEST_QUESTION_ID)).willReturn(
				Optional.of(mockAnswer));
			//when
			Answer answer = answerQueryService.getAnswerByUserIdAndQuestionId(TestConst.TEST_USER_ID,
				TestConst.TEST_QUESTION_ID);
			//then
			Assertions.assertThat(answer).usingRecursiveComparison().isEqualTo(mockAnswer);
		}

		@Test
		void 답변이_존재하지_않으면_예외가_발생한다() {
			//given
			given(answerRepository.findByWriterIdAndQuestionId(TestConst.TEST_USER_ID, TestConst.TEST_QUESTION_ID)).willReturn(
				Optional.empty());
			//when
			//then
			Assertions.assertThatThrownBy(() -> answerQueryService.getAnswerByUserIdAndQuestionId(TestConst.TEST_USER_ID, TestConst.TEST_QUESTION_ID))
				.isInstanceOf(AnswerNotFoundException.class);
		}
	}

}