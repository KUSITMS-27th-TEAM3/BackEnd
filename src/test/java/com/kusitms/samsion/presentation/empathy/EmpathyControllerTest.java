package com.kusitms.samsion.presentation.empathy;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kusitms.samsion.application.empathy.EmpathyToggleService;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.presentation.config.CommonRestDocs;

@WebMvcTest(EmpathyController.class)
@DisplayName("EmpathyController 테스트")
class EmpathyControllerTest extends CommonRestDocs {

	@MockBean
	private EmpathyToggleService empathyToggleService;

	@Test
	void 접속중인_앨범에서_공감_GET_요청이_정상적으로_들어온다() throws Exception {
		//given
		MockHttpServletRequestBuilder request = RestDocumentationRequestBuilders.get("/album/{albumId}/empathy", 1L).header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token");
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
						parameterWithName("albumId").description("앨범 ID")
					)
				)
			);
	}

}