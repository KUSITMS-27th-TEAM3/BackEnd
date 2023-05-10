package com.kusitms.samsion.presentation.comment;


import com.kusitms.samsion.application.comment.dto.request.CommentCreateRequest;
import com.kusitms.samsion.application.comment.dto.response.CommentInfoResponse;
import com.kusitms.samsion.application.comment.service.CommentCreateService;
import com.kusitms.samsion.application.comment.service.CommentUpdateService;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.presentation.config.CommonRestDocs;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@DisplayName("CommentController 테스트")
public class CommentControllerTest extends CommonRestDocs{

    @MockBean
    private CommentCreateService commentCreateService;
    @MockBean
    private CommentUpdateService commentUpdateService;

    @Test
    void 댓글_저장() throws Exception {
        //given
        Long albumId = 1L;
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("test");
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .description("test")
                .writer("test")
                .writerProfileImageUrl("test")
                .build();
        given(commentCreateService.createComment(any(), any())).willReturn(commentInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                post("/album/{albumId}/comment", albumId)
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
        Long albumId = 1L;
        Long commentId = 1L;
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("test");
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .description("test")
                .writer("test")
                .writerProfileImageUrl("test")
                .build();
        given(commentCreateService.createReComment(any(), any(), any())).willReturn(commentInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                post("/album/{albumId}/comment/{commentId}", albumId, commentId)
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
        Long commentId = 1L;
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest("test");
        CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                .description("test")
                .writer("test")
                .writerProfileImageUrl("test")
                .build();
        given(commentUpdateService.updateComment(any(), any())).willReturn(commentInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                put("/album/comment/{commentId}", commentId)
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

}
