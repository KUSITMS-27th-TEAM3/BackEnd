package com.kusitms.samsion.domain.question.presentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.slice.PageResponse;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.domain.question.application.dto.request.AnswerCreateRequest;
import com.kusitms.samsion.domain.question.application.dto.request.AnswerUpdateRequest;
import com.kusitms.samsion.domain.question.application.dto.response.QuestionInfoResponse;
import com.kusitms.samsion.domain.question.application.service.AnswerCreateUseCase;
import com.kusitms.samsion.domain.question.application.service.AnswerUpdateUseCase;
import com.kusitms.samsion.domain.question.application.service.QuestionReadUseCase;
import com.kusitms.samsion.common.config.CommonRestDocs;

@WebMvcTest(QuestionController.class)
@DisplayName("QuestionController 테스트")
class QuestionControllerTest extends CommonRestDocs {

	@MockBean
	QuestionReadUseCase questionReadUseCase;

	@MockBean
	AnswerCreateUseCase answerCreateUseCase;

	@MockBean
	AnswerUpdateUseCase answerUpdateUseCase;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void 모든_질문을_조회한다() throws Exception {
		//given
		Pageable pageRequest = SliceTestUtils.getMockPageable();
		QuestionInfoResponse questionInfoResponse = QuestionInfoResponse.builder()
			.questionId(TestConst.TEST_QUESTION_ID)
			.questionTitle(TestConst.TEST_QUESTION_TITLE)
			.answerDescription(TestConst.TEST_ANSWER_DESCRIPTION)
			.build();
		PageResponse<QuestionInfoResponse> mockPageResponse = SliceTestUtils.getMockPageResponse(questionInfoResponse);
		given(questionReadUseCase.getQuestionList(pageRequest)).willReturn(mockPageResponse);

		MockHttpServletRequestBuilder request = get(TestConst.QUESTION_PREFIX_URL).header(
			ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);
		//when
		ResultActions result = mockMvc.perform(request
			.param("page", String.valueOf(pageRequest.getPageNumber()))
			.param("size", String.valueOf(pageRequest.getPageSize())));
		//then
		result.andExpect(status().isOk())
			.andDo(
				restDocs.document(
					requestHeaders(
						headerWithName("Authorization").description("access token")
					),
					requestParameters(
						parameterWithName("page").description("페이지 번호"),
						parameterWithName("size").description("페이지 사이즈")
					),
					responseFields(
						fieldWithPath("content[].questionId").description("질문 ID"),
						fieldWithPath("content[].questionTitle").description("질문 제목"),
						fieldWithPath("content[].answerDescription").description("답변 내용"),
						fieldWithPath("pageNumber").description("페이지 번호"),
						fieldWithPath("pageSize").description("페이지 사이즈"),
						fieldWithPath("totalElements").description("전체 요소 수"),
						fieldWithPath("totalPages").description("전체 페이지 수"),
						fieldWithPath("last").description("마지막 페이지 여부")
					)
				)
			);
	}

	@Test
	void 질문에_대한_답변을_작성한다() throws Exception {
		//given
		AnswerCreateRequest answerCreateRequest = new AnswerCreateRequest(TestConst.TEST_ANSWER_DESCRIPTION);
		String content = objectMapper.writeValueAsString(answerCreateRequest);
		MockHttpServletRequestBuilder request = RestDocumentationRequestBuilders.post(TestConst.QUESTION_PREFIX_URL + "/{questionId}", TestConst.TEST_QUESTION_ID)
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
			.header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);
		//when
		ResultActions result = mockMvc.perform(request);
		//then
		result.andExpect(status().isOk())
			.andDo(
				restDocs.document(
					requestHeaders(
						headerWithName("Authorization").description("access token")
					),
					pathParameters(
						parameterWithName("questionId").description("질문 ID")
					),
					requestFields(
						fieldWithPath("answerDescription").description("답변 내용")
					)
				)
			);
	}

	@Test
	void 질문을_수정한다() throws Exception {
		//given
		AnswerUpdateRequest answerUpdateRequest = new AnswerUpdateRequest(TestConst.TEST_ANSWER_DESCRIPTION);
		String content = objectMapper.writeValueAsString(answerUpdateRequest);
		MockHttpServletRequestBuilder request = RestDocumentationRequestBuilders.put(
				TestConst.QUESTION_PREFIX_URL + "/{questionId}", TestConst.TEST_QUESTION_ID)
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
			.header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);
		//when
		ResultActions result = mockMvc.perform(request);
		//then
		result.andExpect(status().isOk())
			.andDo(
				restDocs.document(
					requestHeaders(
						headerWithName("Authorization").description("access token")
					),
					pathParameters(
						parameterWithName("questionId").description("질문 ID")
					),
					requestFields(
						fieldWithPath("answerDescription").description("답변 내용")
					)
				)
			);
	}



}