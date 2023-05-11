package com.kusitms.samsion.domain.question.service;

import static org.mockito.BDDMockito.*;

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
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.question.entity.Answer;
import com.kusitms.samsion.domain.question.entity.Question;
import com.kusitms.samsion.domain.question.exception.AnswerAlreadyExistException;
import com.kusitms.samsion.domain.question.repository.AnswerRepository;
import com.kusitms.samsion.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("AnswerSaveService 테스트")
class AnswerSaveServiceTest {

	@Mock
	AnswerRepository answerRepository;

	AnswerSaveService answerSaveService;

	@BeforeEach
	void setUp() {
		answerSaveService = new AnswerSaveService(answerRepository);
	}

	@Nested
	class saveAnswer_메서드에서 {

		@Test
		void 답변이_존재하면_예외가_발생한다() {
			//given
			User mockUser = UserTestUtils.getMockUser();
			Question mockQuestion = QuestionTestUtils.getMockQuestion();
			given(answerRepository.existsByWriterIdAndQuestionId(mockUser.getId(), mockQuestion.getId())).willReturn(true);
			//when
			//then
			Assertions.assertThatThrownBy(()->answerSaveService.saveAnswer(TestConst.TEST_ANSWER_DESCRIPTION,mockUser, mockQuestion))
				.isInstanceOf(AnswerAlreadyExistException.class);
		}

		@Test
		void 답변이_존재하지_않으면_정상적으로_저장된다() {
			//given
			User mockUser = UserTestUtils.getMockUser();
			Question mockQuestion = QuestionTestUtils.getMockQuestion();
			given(answerRepository.existsByWriterIdAndQuestionId(mockUser.getId(), mockQuestion.getId())).willReturn(false);
			//when
			answerSaveService.saveAnswer(TestConst.TEST_ANSWER_DESCRIPTION,mockUser, mockQuestion);
			//then
			then(answerRepository).should(times(1)).save(any(Answer.class));
		}
	}

}