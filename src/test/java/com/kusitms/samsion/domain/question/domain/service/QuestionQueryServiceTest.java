package com.kusitms.samsion.domain.question.domain.service;

import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kusitms.samsion.common.util.QuestionTestUtils;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.question.domain.entity.Question;
import com.kusitms.samsion.domain.question.domain.exception.QuestionNotFoundException;
import com.kusitms.samsion.domain.question.domain.repository.QuestionRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("QuestionQueryService 테스트")
class QuestionQueryServiceTest {

	@Mock
	QuestionRepository questionRepository;

	QuestionQueryService questionQueryService;

	@BeforeEach
	void setUp() {
		questionQueryService = new QuestionQueryService(questionRepository);
	}

	@Test
	void 질문_전체_조회를_한다(){
		//given
		Pageable pageRequest = SliceTestUtils.getMockPageable();
		User mockUser = UserTestUtils.getMockUser();
		Question mockQuestion = QuestionTestUtils.getMockQuestion();
		Page<Question> questions = new PageImpl<>(List.of(mockQuestion));
		given(questionRepository.findAll(pageRequest)).willReturn(questions);
		//when
		Page<Question> result = questionQueryService.findAll(pageRequest, mockUser.getId());
		//then
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(questions);
	}

	@Nested
	class getQuestionById메서드에서{
		@Test
		void 질문이_존재하면_반환한다(){
			//given
			Question mockQuestion = QuestionTestUtils.getMockQuestion();
			given(questionRepository.findById(mockQuestion.getId())).willReturn(Optional.of(mockQuestion));
			//when
			Question question = questionQueryService.getQuestionById(mockQuestion.getId());
			//then
			Assertions.assertThat(question).usingRecursiveComparison().isEqualTo(mockQuestion);
		}

		@Test
		void 질문이_존재하지_않으면_예외가_발생한다(){
			//given
			given(questionRepository.findById(any())).willReturn(Optional.empty());
			//when
			//then
			Assertions.assertThatThrownBy(() -> questionQueryService.getQuestionById(any()))
				.isInstanceOf(QuestionNotFoundException.class);
		}
	}

}