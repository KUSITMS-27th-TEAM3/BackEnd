package com.kusitms.samsion.domain.comment.application.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.CommentTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.exception.NotSameUserException;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.comment.domain.service.CommentValidAccessService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentUpdateUseCase 테스트")
public class CommentUpdateUseCaseTest {
    @Mock
    private UserUtils userUtils;
    @Mock
    private CommentQueryService commentQueryService;
    @Mock
    private CommentValidAccessService commentValidAccessService;
    private CommentUpdateUseCase commentUpdateUseCase;


    @BeforeEach
    public void setUp() {
        commentUpdateUseCase = new CommentUpdateUseCase( userUtils, commentQueryService, commentValidAccessService);
    }

    @Test
    public void 댓글을_수정한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Comment mockComment = CommentTestUtils.getMockComment(mockUser, mockAlbum);
        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest(TestConst.TEST_UPDATE_COMMENT_DESCRIPTION);
        given(userUtils.getUser()).willReturn(mockUser);
        given(commentQueryService.getCommentById(mockComment.getId())).willReturn(mockComment);
        doNothing().when(commentValidAccessService).validateAccess(mockComment, mockUser.getId());
        //when
        CommentInfoResponse commentInfoResponse = commentUpdateUseCase.updateComment(mockComment.getId(), commentUpdateRequest);

        //then
        Assertions.assertThat(commentInfoResponse).isNotNull();
        Assertions.assertThat(commentInfoResponse.isChangeable()).isEqualTo(Boolean.TRUE);
        Assertions.assertThat(commentInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockUser.getMypet().getPetImageUrl());
        Assertions.assertThat(commentInfoResponse.getDescription()).isEqualTo(commentUpdateRequest.getDescription());
        Assertions.assertThat(commentInfoResponse.getWriter()).isEqualTo(mockUser.getNickname());
    }

    @Test
    public void 댓글을_수정시_유저가_다르면_에러_발생() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        User anotherUser = UserTestUtils.getAnotherMockUser(); // 다른 유저 생성
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Comment mockComment = CommentTestUtils.getMockComment(anotherUser, mockAlbum); // 다른 유저가 작성한 댓글 생성
        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest(TestConst.TEST_UPDATE_COMMENT_DESCRIPTION);
        given(userUtils.getUser()).willReturn(mockUser);
        given(commentQueryService.getCommentById(mockComment.getId())).willReturn(mockComment);
        doThrow(NotSameUserException.class).when(commentValidAccessService).validateAccess(mockComment, mockUser.getId());
        //when
        //then
        Assertions.assertThatThrownBy(() -> {
            commentUpdateUseCase.updateComment(mockComment.getId(), commentUpdateRequest);
        }).isInstanceOf(NotSameUserException.class);

    }

}
