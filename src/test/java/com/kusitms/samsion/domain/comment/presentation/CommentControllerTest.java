package com.kusitms.samsion.domain.comment.presentation;

import com.kusitms.samsion.common.config.CommonRestDocs;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentCreateRequest;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.service.CommentCreateUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentDeleteUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentUpdateUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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

    @Test
    void 댓글_저장() throws Exception {
        //given
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(TestConst.TEST_COMMENT_DESCRIPTION);
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .description(TestConst.TEST_COMMENT_DESCRIPTION)
                .writer(TestConst.TEST_NICKNAME)
                .writerProfileImageUrl(TestConst.TEST_PET_IMAGE_URL)
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
                                        fieldWithPath("description").description("댓글 내용"),
                                        fieldWithPath("writer").description("댓글 작성자"),
                                        fieldWithPath("writerProfileImageUrl").description("작성자 프로필 사진")
                                )
                        )
                );
    }

    @Test
    void 대댓글_저장() throws Exception {
        //given
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(TestConst.TEST_CHILD_COMMENT_DESCRIPTION);
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .description(TestConst.TEST_CHILD_COMMENT_DESCRIPTION)
                .writer(TestConst.TEST_NICKNAME)
                .writerProfileImageUrl(TestConst.TEST_PET_IMAGE_URL)
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
                                        fieldWithPath("description").description("댓글 내용"),
                                        fieldWithPath("writer").description("댓글 작성자"),
                                        fieldWithPath("writerProfileImageUrl").description("작성자 프로필 사진")
                                )
                        )
                );
    }


    @Test
    void 댓글_수정() throws Exception {
        //given
        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest(TestConst.TEST_UPDATE_COMMENT_DESCRIPTION);
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .description(TestConst.TEST_UPDATE_COMMENT_DESCRIPTION)
                .writer(TestConst.TEST_NICKNAME)
                .writerProfileImageUrl(TestConst.TEST_PET_IMAGE_URL)
                .build();
        given(commentUpdateUseCase.updateComment(any(), any())).willReturn(commentInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                put("/album/comment/{commentId}", TestConst.TEST_COMMENT_ID)
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
                                        parameterWithName("commentId").description("댓글 ID")
                                ),
                                requestFields(
                                        fieldWithPath("description").description("댓글내용")
                                ),
                                responseFields(
                                        fieldWithPath("description").description("댓글 내용"),
                                        fieldWithPath("writer").description("댓글 작성자"),
                                        fieldWithPath("writerProfileImageUrl").description("작성자 프로필 사진")
                                )
                        )
                );
    }

    @Test
    void 댓글_삭제() throws Exception {
        //given
        //when
        ResultActions result = mockMvc.perform(
                delete("/album/comment/{commentId}", TestConst.TEST_COMMENT_ID)
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
                                        parameterWithName("commentId").description("댓글 ID")
                                )
                        )
                );
    }
}
