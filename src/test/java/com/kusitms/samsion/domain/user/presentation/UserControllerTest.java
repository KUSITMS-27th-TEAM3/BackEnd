package com.kusitms.samsion.domain.user.presentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.domain.user.application.service.MyPetInfoUseCase;
import com.kusitms.samsion.domain.user.application.service.MyPetUpdateUseCase;
import com.kusitms.samsion.common.config.CommonRestDocs;

@WebMvcTest(UserController.class)
@DisplayName("UserController 테스트")
public class UserControllerTest extends CommonRestDocs {

	@MockBean
	private MyPetInfoUseCase myPetInfoUseCase;
	@MockBean
	private MyPetUpdateUseCase myPetUpdateUseCase;

	@Test
	void 접속한_사용자의_mypet_조회() throws Exception {
		//given
		MyPetResponse myPetResponse = MyPetResponse.builder()
			.description(TestConst.TEST_DESCRIPTION)
			.petImageUrl(TestConst.TEST_PET_IMAGE_URL)
			.petName(TestConst.TEST_PET_NAME)
			.petAge(TestConst.TEST_PET_AGE)
			.petType(TestConst.TEST_PET_TYPE)
			.build();
		MockHttpServletRequestBuilder request = get("/user/mypet").header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);
		given(myPetInfoUseCase.getMyPetInfo()).willReturn(myPetResponse);
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
						fieldWithPath("petNickname").description("반려동물 별명"),
						fieldWithPath("petImageUrl").description("반려동물 이미지 URL"),
						fieldWithPath("description").description("반려동물 설명"),
						fieldWithPath("petAge").description("반려동물 나이"),
						fieldWithPath("petType").description("반려동물 종류")
					)
				)
			);
	}

	@Test
	void 접속한_사용자의_mypet_수정() throws Exception {
		//given
		MockMultipartFile petImage = new MockMultipartFile("petImage","test.png","image/png", "test".getBytes());
		MockMultipartFile profileImage = new MockMultipartFile("profileImage","test.png","image/png", "test".getBytes());

		MyPetResponse myPetResponse = MyPetResponse.builder()
			.description(TestConst.TEST_UPDATE_DESCRIPTION)
			.petImageUrl(TestConst.TEST_UPDATE_PET_IMAGE_URL)
			.petName(TestConst.TEST_UPDATE_PET_NAME)
			.petAge(TestConst.TEST_UPDATE_PET_AGE)
			.petType(TestConst.TEST_UPDATE_PET_TYPE)
			.build();
		given(myPetUpdateUseCase.updateMyPetInfo(any())).willReturn(myPetResponse);

		MockHttpServletRequestBuilder request = multipart("/user/mypet")
			.file(petImage)
			.file(profileImage)
			.param("petName", TestConst.TEST_UPDATE_PET_NAME)
			.param("description", TestConst.TEST_UPDATE_DESCRIPTION)
			.param("petAge", String.valueOf(TestConst.TEST_UPDATE_PET_AGE))
			.param("petType", TestConst.TEST_UPDATE_PET_TYPE)
			.param("petNickname", TestConst.TEST_UPDATE_PET_NICKNAME)
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
					requestPartBody("petImage"),
					requestPartBody("profileImage"),
					requestParameters(
						parameterWithName("petName").description("반려동물 이름"),
						parameterWithName("description").description("반려동물 설명"),
						parameterWithName("petAge").description("반려동물 나이"),
						parameterWithName("petType").description("반려동물 종류"),
						parameterWithName("petNickname").description("반려동물 별명")
					),
					responseFields(
						fieldWithPath("petName").description("반려동물 이름"),
						fieldWithPath("petNickname").description("반려동물 별명"),
						fieldWithPath("petImageUrl").description("반려동물 이미지 URL"),
						fieldWithPath("description").description("반려동물 설명"),
						fieldWithPath("petAge").description("반려동물 나이"),
						fieldWithPath("petType").description("반려동물 종류")
					)
				)
			);
	}



}
