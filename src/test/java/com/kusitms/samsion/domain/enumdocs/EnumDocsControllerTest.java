package com.kusitms.samsion.domain.enumdocs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kusitms.samsion.common.config.CommonRestDocs;
import com.kusitms.samsion.common.restdocs.CustomResponseFieldsSnippet;

@WebMvcTest(EnumDocsController.class)
@DisplayName("EnumDocsController 테스트")
class EnumDocsControllerTest extends CommonRestDocs {

	private static CustomResponseFieldsSnippet customResponseFields
		(String type,
			PayloadSubsectionExtractor<?> subsectionExtractor,
			Map<String, Object> attributes, FieldDescriptor... descriptors) {
		return new CustomResponseFieldsSnippet(type, Arrays.asList(descriptors), attributes
			, true, subsectionExtractor);
	}

	private static FieldDescriptor[] enumDocsResponseFieldDescriptor(Map<String, String> enumValues) {
		return enumValues.entrySet().stream()
			.map(entry -> fieldWithPath(entry.getKey()).description(entry.getValue()))
			.toArray(FieldDescriptor[]::new);
	}

	@Test
	void enumDocs_조회() throws Exception {
		//given
		MockHttpServletRequestBuilder request = get("/enumdocs").contentType(
			MediaType.APPLICATION_JSON);
		//when
		ResultActions result = mockMvc.perform(request);
		EnumDocs enumDocs = getData(result.andReturn());
		//then
		result.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("enumdocs",
				customResponseFields("response",
					beneathPath("emotionTagMap").withSubsectionId("emotionTagMap"),
					attributes(key("title").value("감정 태그 목록 조회")),
					enumDocsResponseFieldDescriptor(enumDocs.getEmotionTagMap())
				),
				customResponseFields("response",
					beneathPath("visibilityMap").withSubsectionId("visibilityMap"),
					attributes(key("title").value("공개 범위 목록 조회")),
					enumDocsResponseFieldDescriptor(enumDocs.getVisibilityMap())
				),
				customResponseFields("response",
					beneathPath("sortTypeMap").withSubsectionId("sortTypeMap"),
					attributes(key("title").value("정렬 타입 목록 조회")),
					enumDocsResponseFieldDescriptor(enumDocs.getSortTypeMap())
				)));
	}

	private EnumDocs getData(MvcResult result) throws IOException {
		return objectMapper.readValue(result.getResponse().getContentAsByteArray(), EnumDocs.class);
	}

}