package com.kusitms.samsion.domain.comment.application.service;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.CommentTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentUpdateService 테스트")
public class CommentUpdateUseCaseTest {
    @Mock
    private CommentQueryService commentQueryService;
    private CommentUpdateUseCase commentUpdateUseCase;

    @BeforeEach
    public void setUp() {
        commentUpdateUseCase = new CommentUpdateUseCase(commentQueryService);
    }

    @Test
    public void 댓글을_수정한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Comment mockComment = CommentTestUtils.getMockComment(mockUser, mockAlbum);
        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest(TestConst.TEST_UPDATE_COMMENT_DESCRIPTION);
        given(commentQueryService.getCommentById(mockComment.getId())).willReturn(mockComment);

        //when
        CommentInfoResponse commentInfoResponse = commentUpdateUseCase.updateComment(mockComment.getId(), commentUpdateRequest);

        //then
        Assertions.assertThat(commentInfoResponse).isNotNull();
        Assertions.assertThat(commentInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockUser.getMypet().getPetImageUrl());
        Assertions.assertThat(commentInfoResponse.getDescription()).isEqualTo(commentUpdateRequest.getDescription());
        Assertions.assertThat(commentInfoResponse.getWriter()).isEqualTo(mockUser.getNickname());
    }

}
