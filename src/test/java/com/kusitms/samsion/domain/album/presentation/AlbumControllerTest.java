package com.kusitms.samsion.domain.album.presentation;

import com.kusitms.samsion.common.config.CommonRestDocs;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.application.service.AlbumCreateUseCase;
import com.kusitms.samsion.domain.album.application.service.AlbumDeleteUseCase;
import com.kusitms.samsion.domain.album.application.service.AlbumReadUseCase;
import com.kusitms.samsion.domain.album.application.service.AlbumUpdateUseCase;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlbumController.class)
@DisplayName("AlbumController 테스트")
class AlbumControllerTest extends CommonRestDocs {

    private static final String ALBUM_URL = "/album";
    @MockBean
    AlbumReadUseCase albumReadUseCase;
    @MockBean
    AlbumCreateUseCase albumCreateUseCase;
    @MockBean
    AlbumUpdateUseCase albumUpdateUseCase;
    @MockBean
    AlbumDeleteUseCase albumDeleteUseCase;

    private static AlbumSimpleResponse getMockAlbumSimpleResponse() {
        return AlbumSimpleResponse.builder()
                .title(TestConst.TEST_ALBUM_TITLE)
                .albumId(TestConst.TEST_ALBUM_ID)
                .imageUrl(TestConst.TEST_ALBUM_IMAGE_URL)
                .empathyCount(TestConst.TEST_EMPATHY_COUNT)
                .commentCount(TestConst.TEST_COMMENT_COUNT)
                .build();
    }

    private static AlbumInfoResponse getMockAlbumInfoResponse() {
        return AlbumInfoResponse.builder()
                .imageUrlList(List.of(TestConst.TEST_ALBUM_IMAGE_URL))
                .title(TestConst.TEST_ALBUM_TITLE)
                .description(TestConst.TEST_ALBUM_DESCRIPTION)
                .visibility(Visibility.PUBLIC)
                .changeable(true)
                .writer(TestConst.TEST_NICKNAME)
                .petName(TestConst.TEST_PET_NAME)
                .writerProfileImageUrl(TestConst.TEST_PROFILE_IMAGE_URL)
                .accessUserProfileImageUrl(TestConst.TEST_PROFILE_IMAGE_URL)
                .empathyCount(TestConst.TEST_EMPATHY_COUNT)
                .commentCount(TestConst.TEST_COMMENT_COUNT)
                .emotionTagList(List.of(EmotionTag.COMFORTABLE))
                .build();
    }

    @Test
    void 모든_앨범을_조회한다() throws Exception {
        // given
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        AlbumSimpleResponse mockAlbumSimpleResponse = getMockAlbumSimpleResponse();
        SliceResponse<AlbumSimpleResponse> mockSliceResponse = SliceTestUtils.getMockSliceResponse(
                mockAlbumSimpleResponse);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("emotionTagList", "HAPPY");
        params.add("sortType", "DEFAULT");

        given(albumReadUseCase.getAlbumList(any(), any())).willReturn(mockSliceResponse);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(ALBUM_URL)
                .param("size", String.valueOf(mockPageable.getPageSize()))
                .param("page", String.valueOf(mockPageable.getPageNumber()))
                .params(params);
        // when
        ResultActions result = mockMvc.perform(request);
        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                        requestParameters(
                                parameterWithName("size").description("페이지 사이즈"),
                                parameterWithName("page").description("현재 페이지 번호"),
                                parameterWithName("emotionTagList").description("감정 태그 리스트, 감정 태그 리스트 참조"),
                                parameterWithName("sortType").description("정렬 타입, 정렬 타입 리스트 참조")
                        ),
                        responseFields(
                                fieldWithPath("content[].title").description("앨범 제목"),
                                fieldWithPath("content[].albumId").description("앨범 아이디"),
                                fieldWithPath("content[].imageUrl").description("앨범 이미지 URL"),
                                fieldWithPath("content[].empathyCount").description("공감 수"),
                                fieldWithPath("content[].commentCount").description("댓글 수"),
                                fieldWithPath("size").description("페이지 사이즈"),
                                fieldWithPath("page").description("현재 페이지 번호"),
                                fieldWithPath("hasNext").description("다음 페이지 여부")
                        )
                ));
    }

    @Test
    void 개인_앨범_전체_조회() throws Exception {
        // given
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        AlbumSimpleResponse mockAlbumSimpleResponse = getMockAlbumSimpleResponse();
        SliceResponse<AlbumSimpleResponse> mockSliceResponse = SliceTestUtils.getMockSliceResponse(
                mockAlbumSimpleResponse);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("emotionTagList", "HAPPY");
        params.add("sortType", "DEFAULT");

        given(albumReadUseCase.getMyAlbumList(any(), any())).willReturn(mockSliceResponse);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(ALBUM_URL + "/private")
                .param("size", String.valueOf(mockPageable.getPageSize()))
                .param("page", String.valueOf(mockPageable.getPageNumber()))
                .params(params)
                .header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);
        // when
        ResultActions result = mockMvc.perform(request);
        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName("Authorization").description("access token")
                        ),
                        requestParameters(
                                parameterWithName("size").description("페이지 사이즈"),
                                parameterWithName("page").description("현재 페이지 번호"),
                                parameterWithName("emotionTagList").description("감정 태그 리스트, 감정 태그 리스트 참조"),
                                parameterWithName("sortType").description("정렬 타입, 정렬 타입 리스트 참조")
                        ),
                        responseFields(
                                fieldWithPath("content[].title").description("앨범 제목"),
                                fieldWithPath("content[].albumId").description("앨범 아이디"),
                                fieldWithPath("content[].imageUrl").description("앨범 이미지 URL"),
                                fieldWithPath("content[].empathyCount").description("공감 수"),
                                fieldWithPath("content[].commentCount").description("댓글 수"),
                                fieldWithPath("size").description("페이지 사이즈"),
                                fieldWithPath("page").description("현재 페이지 번호"),
                                fieldWithPath("hasNext").description("다음 페이지 여부")
                        )
                ));
    }

    @Test
    void 앨범_상세_조회() throws Exception {
        // given
        AlbumInfoResponse mockAlbumInfoResponse = getMockAlbumInfoResponse();
        given(albumReadUseCase.getAlbum(any())).willReturn(mockAlbumInfoResponse);

        MockHttpServletRequestBuilder request = RestDocumentationRequestBuilders.get(ALBUM_URL + "/{albumId}",
                        TestConst.TEST_ALBUM_ID)
                .header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);
        // when
        ResultActions result = mockMvc.perform(request);
        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName("Authorization").description("access token")
                        ),
                        pathParameters(
                                parameterWithName("albumId").description("앨범 아이디")
                        ),
                        responseFields(
                                fieldWithPath("imageUrlList[]").description("앨범 이미지 URL 리스트"),
                                fieldWithPath("title").description("앨범 제목"),
                                fieldWithPath("description").description("앨범 설명"),
                                fieldWithPath("visibility").description("공개 여부"),
                                fieldWithPath("changeable").description("수정 가능 여부"),
                                fieldWithPath("writer").description("앨범 작성자"),
                                fieldWithPath("petName").description("앨범 작성자의 반려동물 이름"),
                                fieldWithPath("writerProfileImageUrl").description("앨범 작성자의 프로필 이미지 URL"),
                                fieldWithPath("accessUserProfileImageUrl").description("앨범 접근자의 프로필 이미지 URL"),
                                fieldWithPath("empathyCount").description("공감 수"),
                                fieldWithPath("commentCount").description("댓글 수"),
                                fieldWithPath("emotionTagList").description("감정 태그 리스트, 감정 태그 리스트 참조")
                        )
                ));
    }

    @Test
    void 앨범_생성() throws Exception {
        // given
        AlbumInfoResponse mockAlbumInfoResponse = getMockAlbumInfoResponse();
        given(albumCreateUseCase.createAlbum(any())).willReturn(mockAlbumInfoResponse);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("title", TestConst.TEST_ALBUM_TITLE);
        params.add("description", TestConst.TEST_ALBUM_DESCRIPTION);
        params.add("visibility", String.valueOf(Visibility.PRIVATE));
        params.add("emotionTags", String.valueOf(EmotionTag.JOY));

        MockMultipartFile multipartFile = new MockMultipartFile("albumImages", "albumImages.jpeg", "image/jpeg",
                "albumImages".getBytes());
        MockHttpServletRequestBuilder request = multipart(ALBUM_URL)
                .file(multipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .params(params)
                .header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);

        // when
        ResultActions result = mockMvc.perform(request);
        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName("Authorization").description("access token")
                        ),
                        requestPartBody("albumImages"),
                        requestParameters(
                                parameterWithName("title").description("앨범 제목"),
                                parameterWithName("description").description("앨범 설명"),
                                parameterWithName("visibility").description("앨범 공개 여부, PUBLIC 또는 PRIVATE"),
                                parameterWithName("emotionTags").description("감정 태그 리스트, 감정 태그 리스트 참조")
                        ),
                        responseFields(
                                fieldWithPath("imageUrlList[]").description("앨범 이미지 URL 리스트"),
                                fieldWithPath("title").description("앨범 제목"),
                                fieldWithPath("description").description("앨범 설명"),
                                fieldWithPath("visibility").description("공개 여부"),
                                fieldWithPath("changeable").description("수정 가능 여부"),
                                fieldWithPath("writer").description("앨범 작성자"),
                                fieldWithPath("petName").description("앨범 작성자의 반려동물 이름"),
                                fieldWithPath("writerProfileImageUrl").description("앨범 작성자의 프로필 이미지 URL"),
                                fieldWithPath("accessUserProfileImageUrl").description("앨범 접근자의 프로필 이미지 URL"),
                                fieldWithPath("empathyCount").description("공감 수"),
                                fieldWithPath("commentCount").description("댓글 수"),
                                fieldWithPath("emotionTagList").description("감정 태그 리스트, 감정 태그 리스트 참조")
                        )
                ));
    }

    @Test
    void 앨범_수정() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("title", TestConst.TEST_UPDATE_ALBUM_TITLE);
        params.add("description", TestConst.TEST_UPDATE_ALBUM_DESCRIPTION);
        params.add("visibility", String.valueOf(Visibility.PRIVATE));
        params.add("emotionTags", String.valueOf(EmotionTag.JOY));
        params.add("imageUrlList", TestConst.TEST_UPDATE_ALBUM_IMAGE_URL);

        MockMultipartFile multipartFile = new MockMultipartFile("albumImages", "albumImages.jpeg", "image/jpeg",
                "albumImages".getBytes());
        MockHttpServletRequestBuilder request = multipart(ALBUM_URL + "/{albumId}", TestConst.TEST_ALBUM_ID)
                .file(multipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .params(params)
                .header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);

        // when
        ResultActions result = mockMvc.perform(request);
        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName("Authorization").description("access token")
                        ),
                        requestPartBody("albumImages"),
                        pathParameters(
                                parameterWithName("albumId").description("앨범 아이디")
                        ),
                        requestParameters(
                                parameterWithName("title").description("앨범 제목"),
                                parameterWithName("description").description("앨범 설명"),
                                parameterWithName("visibility").description("앨범 공개 여부, PUBLIC 또는 PRIVATE"),
                                parameterWithName("emotionTags").description("감정 태그 리스트, 감정 태그 리스트 참조"),
                                parameterWithName("imageUrlList").description("앨범 이미지 URL 리스트")
                        )));
    }

    @Test
    void 앨범_삭제() throws Exception {
        // given
        MockHttpServletRequestBuilder request = delete(ALBUM_URL + "/{albumId}", TestConst.TEST_ALBUM_ID)
                .header(ApplicationConst.ACCESS_TOKEN_HEADER, TestConst.TEST_ACCESS_TOKEN);
        // when
        ResultActions result = mockMvc.perform(request);
        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName("Authorization").description("access token")
                        ),
                        pathParameters(
                                parameterWithName("albumId").description("앨범 아이디")
                        )
                ));
    }

}