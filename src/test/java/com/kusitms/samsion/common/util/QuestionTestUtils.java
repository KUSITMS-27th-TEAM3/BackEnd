package com.kusitms.samsion.common.util;

import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.question.domain.entity.Answer;
import com.kusitms.samsion.domain.question.domain.entity.Question;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionTestUtils {

	private static final Question mockQuestion = setMockQuestionSingleton();
	private static final Answer mockAnswer = setMockAnswerSingleton();


	public static Question getMockQuestion(){
		return mockQuestion;
	}

	public static Answer getMockAnswer(){
		return mockAnswer;
	}

	private static Question setMockQuestionSingleton() {
		Question question = new Question(TestConst.TEST_QUESTION_TITLE);
		ReflectionTestUtils.setField(question, "id", TestConst.TEST_QUESTION_ID);
		return question;
	}

	private static Answer setMockAnswerSingleton() {
		Answer answer = new Answer(TestConst.TEST_ANSWER_DESCRIPTION,UserTestUtils.getMockUser(), getMockQuestion());
		ReflectionTestUtils.setField(answer, "id", TestConst.TEST_ANSWER_ID);
		return answer;
	}
}
