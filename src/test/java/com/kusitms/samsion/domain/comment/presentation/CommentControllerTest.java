package com.kusitms.samsion.domain.comment.presentation;

import com.kusitms.samsion.common.config.CommonRestDocs;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentCreateRequest;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentCountResponse;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.service.CommentCreateUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentDeleteUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentReadUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentUpdateUseCase;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@DisplayName("CommentController 테스트")
public class CommentControllerTest extends CommonRestDocs{

    @MockBean
    private CommentCreateUseCase commentCreateUseCase;
    @MockBean
    private CommentUpdateUseCase commentUpdateUseCase;
    @MockBean
    private CommentDeleteUseCase commentDeleteUseCase;
    @MockBean
    private CommentReadUseCase commentReadUseCase;

    @Test
    void 댓글_저장() throws Exception {
        //given
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(TestConst.TEST_COMMENT_DESCRIPTION);
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .commentId(TestConst.TEST_COMMENT_ID)
                .description(TestConst.TEST_COMMENT_DESCRIPTION)
                .writer(TestConst.TEST_NICKNAME)
                .writerProfileImageUrl(TestConst.TEST_PET_IMAGE_URL)
                .deleted(false)
                .changeable(true)
                .build();
        given(commentCreateUseCase.createComment(any(), any())).willReturn(commentInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                post("/album/{albumId}/comment", TestConst.TEST_ALBUM_ID)
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequest))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                pathParameters(
                                        parameterWithName("albumId").description("앨범 ID")
                                ),
                                requestFields(
                                        fieldWithPath("description").description("댓글내용")
                                ),
                                responseFields(
                                        fieldWithPath("commentId").description("댓글 ID"),
                                        fieldWithPath("description").description("댓글 내용"),
                                        fieldWithPath("writer").description("댓글 작성자"),
                                        fieldWithPath("writerProfileImageUrl").description("작성자 프로필 사진"),
                                        fieldWithPath("deleted").description("삭제 여부. 삭제 되었으면 true, 아니면 false"),
                                        fieldWithPath("changeable").description("변경 가능 여부")
                                )
                        )
                );
    }

    @Test
    void 대댓글_저장() throws Exception {
        //given
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(TestConst.TEST_CHILD_COMMENT_DESCRIPTION);
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .commentId(TestConst.TEST_CHILD_ID)
                .description(TestConst.TEST_CHILD_COMMENT_DESCRIPTION)
                .writer(TestConst.TEST_NICKNAME)
                .writerProfileImageUrl(TestConst.TEST_PET_IMAGE_URL)
                .deleted(false)
                .changeable(true)
                .build();
        given(commentCreateUseCase.createReComment(any(), any(), any())).willReturn(commentInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                post("/album/{albumId}/comment/{commentId}", TestConst.TEST_ALBUM_ID, TestConst.TEST_COMMENT_ID)
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequest))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                pathParameters(
                                        parameterWithName("albumId").description("앨범 ID"),
                                        parameterWithName("commentId").description("댓글 ID")
                                ),
                                requestFields(
                                        fieldWithPath("description").description("댓글내용")
                                ),
                                responseFields(
                                        fieldWithPath("commentId").description("댓글 ID"),
                                        fieldWithPath("description").description("댓글 내용"),
                                        fieldWithPath("writer").description("댓글 작성자"),
                                        fieldWithPath("writerProfileImageUrl").description("작성자 프로필 사진"),
                                        fieldWithPath("deleted").description("삭제 여부. 삭제 되었으면 true, 아니면 false"),
                                        fieldWithPath("changeable").description("변경 가능 여부")
                                )
                        )
                );
    }


    @Test
    void 댓글_수정() throws Exception {
        //given
        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest(TestConst.TEST_UPDATE_COMMENT_DESCRIPTION);
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .commentId(TestConst.TEST_COMMENT_ID)
                .description(TestConst.TEST_UPDATE_COMMENT_DESCRIPTION)
                .writer(TestConst.TEST_NICKNAME)
                .writerProfileImageUrl(TestConst.TEST_PET_IMAGE_URL)
                .deleted(false)
                .changeable(true)
                .build();
        given(commentUpdateUseCase.updateComment(any(), any())).willReturn(commentInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                put("/album/{albumId}/comment/{commentId}",TestConst.TEST_ALBUM_ID, TestConst.TEST_COMMENT_ID)
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentUpdateRequest))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                pathParameters(
                                        parameterWithName("albumId").description("앨범 ID"),
                                        parameterWithName("commentId").description("댓글 ID")
                                ),
                                requestFields(
                                        fieldWithPath("description").description("댓글내용")
                                ),
                                responseFields(
                                        fieldWithPath("commentId").description("댓글 ID"),
                                        fieldWithPath("description").description("댓글 내용"),
                                        fieldWithPath("writer").description("댓글 작성자"),
                                        fieldWithPath("writerProfileImageUrl").description("작성자 프로필 사진"),
                                        fieldWithPath("deleted").description("삭제 여부. 삭제 되었으면 true, 아니면 false"),
                                        fieldWithPath("changeable").description("변경 가능 여부")
                                )
                        )
                );
    }

    @Test
    void 댓글_삭제() throws Exception {
        //given
        //when
        ResultActions result = mockMvc.perform(
                delete("/album/{albumId}/comment/{commentId}", TestConst.TEST_ALBUM_ID, TestConst.TEST_COMMENT_ID)
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                pathParameters(
                                        parameterWithName("albumId").description("앨범 ID"),
                                        parameterWithName("commentId").description("댓글 ID")
                                )
                        )
                );
    }

    @Test
    void 댓글_조회() throws Exception {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Pageable pageRequest = SliceTestUtils.getMockPageable();
        List<CommentInfoResponse> children = new ArrayList<>(Arrays.asList(new CommentInfoResponse(TestConst.TEST_CHILD_ID,
                TestConst.TEST_CHILD_COMMENT_DESCRIPTION, TestConst.TEST_NICKNAME, TestConst.TEST_PET_IMAGE_URL, false, false)));
        CommentInfoResponse commentInfoResponse = new CommentInfoResponse(TestConst.TEST_COMMENT_ID,TestConst.TEST_COMMENT_DESCRIPTION,
                TestConst.TEST_NICKNAME, TestConst.TEST_PET_IMAGE_URL,false, false, children);
        SliceResponse<CommentInfoResponse> mockPageResponse = SliceTestUtils.getMockSliceResponse(commentInfoResponse);
        given(commentReadUseCase.getCommentList(pageRequest, TestConst.TEST_ALBUM_ID)).willReturn(mockPageResponse);
        //when
        ResultActions result = mockMvc.perform(
                get("/album/{albumId}/comment", TestConst.TEST_ALBUM_ID)
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", String.valueOf(pageRequest.getPageNumber()))
                        .param("size", String.valueOf(pageRequest.getPageSize())));
        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                pathParameters(
                                        parameterWithName("albumId").description("앨범 ID")
                                ),
                                requestParameters(
                                        parameterWithName("page").description("페이지 번호"),
                                        parameterWithName("size").description("페이지 사이즈")
                                ),
                                responseFields(
                                        fieldWithPath("content[].commentId").description("댓글 ID"),
                                        fieldWithPath("content[].description").description("댓글 내용"),
                                        fieldWithPath("content[].writer").description("댓글 작성자"),
                                        fieldWithPath("content[].writerProfileImageUrl").description("작성자 프로필 사진"),
                                        fieldWithPath("content[].deleted").description("삭제 여부. 삭제 되었으면 true, 아니면 false"),
                                        fieldWithPath("content[].changeable").description("변경 가능 여부"),
                                        fieldWithPath("content[].child[].commentId").description("댓글 ID"),
                                        fieldWithPath("content[].child[].description").description("댓글 내용"),
                                        fieldWithPath("content[].child[].writer").description("댓글 작성자"),
                                        fieldWithPath("content[].child[].writerProfileImageUrl").description("작성자 프로필 사진"),
                                        fieldWithPath("content[].child[].deleted").description("삭제 여부. 삭제 되었으면 true, 아니면 false"),
                                        fieldWithPath("content[].child[].changeable").description("변경 가능 여부"),
                                        fieldWithPath("page").description("현재 페이지"),
                                        fieldWithPath("size").description("페이지 사이즈"),
                                        fieldWithPath("hasNext").description("다음 페이지 여부")
                                )
                        )
                );
    }

    @Test
    void 댓글수_반환한다() throws Exception {
        //given
        CommentCountResponse commentCountResponse = new CommentCountResponse(TestConst.TEST_COMMENT_COUNT);
        given(commentReadUseCase.getCommentCount(TestConst.TEST_ALBUM_ID)).willReturn(commentCountResponse);
        MockHttpServletRequestBuilder request = RestDocumentationRequestBuilders.get("/album/{albumId}/comment/count",
                TestConst.TEST_ALBUM_ID).header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token");
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
                                ),
                                responseFields(
                                        fieldWithPath("commentCount").description("댓글 수")
                                )
                        )
                );
    }
}