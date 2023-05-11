package com.kusitms.samsion.application.comment.service;

import com.kusitms.samsion.application.comment.dto.request.CommentCreateRequest;
import com.kusitms.samsion.application.comment.dto.response.CommentInfoResponse;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.CommentTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.service.AlbumQueryService;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.comment.service.CommentQueryService;
import com.kusitms.samsion.domain.comment.service.CommentSaveService;
import com.kusitms.samsion.domain.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentCreateService 테스트")
public class CommentCreateServiceTest {

    @Mock
    private UserUtils userUtils;

    @Mock
    private AlbumQueryService albumQueryService;

    @Mock
    private CommentSaveService commentSaveService;

    @Mock
    private CommentQueryService commentQueryService;

    private CommentCreateService commentCreateService;

    @BeforeEach
    void setUp() {
        commentCreateService = new CommentCreateService(userUtils, albumQueryService, commentSaveService, commentQueryService);
    }

    @Test
    public void 댓글을_생성한다() {
        // given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(TestConst.TEST_COMMENT_DESCRIPTION);

        given(userUtils.getUser()).willReturn(mockUser);
        given(albumQueryService.getAlbumById(mockAlbum.getId())).willReturn(mockAlbum);

        // when
        CommentInfoResponse commentInfoResponse = commentCreateService.createComment(mockAlbum.getId(), commentCreateRequest);

        //then
        then(commentSaveService).should(times(1)).saveComment(any(Comment.class));
        Assertions.assertThat(commentInfoResponse).isNotNull();
        Assertions.assertThat(commentInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockUser.getMypet().getPetImageUrl());
        Assertions.assertThat(commentInfoResponse.getDescription()).isEqualTo(commentCreateRequest.getDescription());
        Assertions.assertThat(commentInfoResponse.getWriter()).isEqualTo(mockUser.getNickname());
    }

    @Test
    public void 대댓글을_생성한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Comment mockParentComment = CommentTestUtils.getMockComment(mockUser, mockAlbum);
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(TestConst.TEST_CHILD_COMMENT_DESCRIPTION);
        given(userUtils.getUser()).willReturn(mockUser);
        given(albumQueryService.getAlbumById(mockAlbum.getId())).willReturn(mockAlbum);
        given(commentQueryService.getCommentById(mockParentComment.getId())).willReturn(mockParentComment);
        //when
        CommentInfoResponse commentInfoResponse = commentCreateService.createReComment(mockAlbum.getId(), mockParentComment.getId(), commentCreateRequest);

        //then
        then(commentSaveService).should(times(1)).saveComment(any(Comment.class));
        Assertions.assertThat(commentInfoResponse).isNotNull();
        Assertions.assertThat(commentInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockUser.getMypet().getPetImageUrl());
        Assertions.assertThat(commentInfoResponse.getDescription()).isEqualTo(commentCreateRequest.getDescription());
        Assertions.assertThat(commentInfoResponse.getWriter()).isEqualTo(mockUser.getNickname());
    }

}
