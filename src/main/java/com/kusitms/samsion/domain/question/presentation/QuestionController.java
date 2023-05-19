package com.kusitms.samsion.domain.question.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.domain.question.application.dto.request.AnswerCreateRequest;
import com.kusitms.samsion.domain.question.application.dto.request.AnswerUpdateRequest;
import com.kusitms.samsion.domain.question.application.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.domain.question.application.service.AnswerCreateUseCase;
import com.kusitms.samsion.domain.question.application.service.AnswerUpdateUseCase;
import com.kusitms.samsion.domain.question.application.service.QuestionReadUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

	private final QuestionReadUseCase questionReadUseCase;
	private final AnswerCreateUseCase answerCreateUseCase;
	private final AnswerUpdateUseCase answerUpdateUseCase;

	@GetMapping
	public SliceResponse<QuestionInfoResponse> getQuestionList(Pageable pageable) {
		return questionReadUseCase.getQuestionList(pageable);
	}

	@PostMapping("/{questionId}")
	public void createAnswer(@PathVariable Long questionId, @RequestBody AnswerCreateRequest answerCreateRequest) {
		answerCreateUseCase.createAnswer(questionId ,answerCreateRequest);
	}

	@PutMapping("/{questionId}")
	public void updateAnswer(@PathVariable Long questionId, @RequestBody AnswerUpdateRequest answerUpdateRequest) {
		answerUpdateUseCase.updateAnswer(questionId, answerUpdateRequest);
	}
}
