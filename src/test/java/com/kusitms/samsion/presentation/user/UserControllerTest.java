package com.kusitms.samsion.presentation.user;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kusitms.samsion.application.user.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.application.user.dto.response.MyPetResponse;
import com.kusitms.samsion.application.user.service.MyPetInfoService;
import com.kusitms.samsion.application.user.service.MyPetUpdateService;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.presentation.config.CommonRestDocs;

@WebMvcTest(UserController.class)
public class UserControllerTest extends CommonRestDocs {

	@MockBean
	private MyPetInfoService myPetInfoService;
	@MockBean
	private MyPetUpdateService myPetUpdateService;

	@Test
	void 접속한_사용자의_mypet_조회() throws Exception {
		//given
		MyPetResponse myPetResponse = MyPetResponse.builder()
			.description("test")
			.petImageUrl("test")
			.petName("test")
			.build();
		MockHttpServletRequestBuilder request = get("/user/mypet").header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token");
		given(myPetInfoService.getMyPetInfo()).willReturn(myPetResponse);
		//when
		ResultActions result = mockMvc.perform(request);
		//then
		result.andExpect(status().isOk())
			.andDo(
				restDocs.document(
					requestHeaders(
						headerWithName("Authorization").description("access token")
					),
					responseFields(
						fieldWithPath("petName").description("반려동물 이름"),
						fieldWithPath("petImageUrl").description("반려동물 이미지 URL"),
						fieldWithPath("description").description("반려동물 설명")
					)
				)
			);
	}

	@Test
	void 접속한_사용자의_mypet_수정() throws Exception {
		//given
		MyPetUpdateRequest myPetUpdateRequest = MyPetUpdateRequest.builder()
			.petName("test")
			.description("test")
			.build();
		MockMultipartFile multipartFile = new MockMultipartFile("file","test.png","image/png", "test".getBytes());

		MyPetResponse myPetResponse = MyPetResponse.builder()
			.description("test")
			.petImageUrl("test")
			.petName("test")
			.build();
		given(myPetUpdateService.updateMyPetInfo(any(), any())).willReturn(myPetResponse);

		MockHttpServletRequestBuilder request = multipart("/user/mypet")
			.file(multipartFile)
			.file(
				new MockMultipartFile("request", "", "application/json",
					objectMapper.writeValueAsString(myPetUpdateRequest).getBytes(StandardCharsets.UTF_8)))
			.contentType(MediaType.MULTIPART_FORM_DATA)
			.header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token");
		//when
		ResultActions result = mockMvc.perform(request);
		//then
		result.andExpect(status().isOk())
			.andDo(
				restDocs.document(
					requestHeaders(
						headerWithName("Authorization").description("access token")
					),
					requestPartBody("file"),
					requestPartBody("request", Map.of("petName", "반려동물 이름", "description", "반려동물 설명")),
					responseFields(
						fieldWithPath("petName").description("반려동물 이름"),
						fieldWithPath("petImageUrl").description("반려동물 이미지 URL"),
						fieldWithPath("description").description("반려동물 설명")
					)
				)
			);
	}



}
