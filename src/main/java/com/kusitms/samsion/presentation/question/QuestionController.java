package com.kusitms.samsion.presentation.question;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.application.question.dto.request.AnswerCreateRequest;
import com.kusitms.samsion.application.question.dto.request.AnswerUpdateRequest;
import com.kusitms.samsion.application.question.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.application.question.service.AnswerCreateService;
import com.kusitms.samsion.application.question.service.AnswerUpdateService;
import com.kusitms.samsion.application.question.service.QuestionReadService;
import com.kusitms.samsion.common.slice.PageResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

	private final QuestionReadService questionReadService;
	private final AnswerCreateService answerCreateService;
	private final AnswerUpdateService answerUpdateService;

	@GetMapping
	public PageResponse<QuestionInfoResponse> getQuestionList(Pageable pageable) {
		return questionReadService.getQuestionList(pageable);
	}

	@PostMapping("/{questionId}")
	public void createAnswer(@PathVariable Long questionId, @RequestBody AnswerCreateRequest answerCreateRequest) {
		answerCreateService.createAnswer(questionId ,answerCreateRequest);
	}

	@PutMapping("/{questionId}")
	public void updateAnswer(@PathVariable Long questionId, @RequestBody AnswerUpdateRequest answerUpdateRequest) {
		answerUpdateService.updateAnswer(questionId, answerUpdateRequest);
	}
}
