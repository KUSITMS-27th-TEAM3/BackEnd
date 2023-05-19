package com.kusitms.samsion.domain.comment.application.service;

import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.CommentTestUtils;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommetReadUseCase 테스트")
public class CommetReadUseCaseTest {

    @Mock
   CommentQueryService commentQueryService;
    CommentReadUseCase commentReadUseCase;

    @BeforeEach
    void setup() {commentReadUseCase = new CommentReadUseCase(commentQueryService); }

    @Test
    void 댓글을_조회한다_대댓글은_존재하지_않는다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Comment mockComment = CommentTestUtils.getMockComment(mockUser, mockAlbum);
        List<Comment> mockCommentList = List.of(mockComment);
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        Slice<Comment> mockPage = SliceTestUtils.getMockSlice(mockCommentList);
        given(commentQueryService.getCommentByAlbumId(mockPageable,mockAlbum.getId())).willReturn(mockPage);

        //when
        SliceResponse<CommentInfoResponse> commentList = commentReadUseCase.getCommentList(mockPageable, mockAlbum.getId());

        //then
        CommentInfoResponse commentInfoResponse = commentList.getContent().get(0);
        Assertions.assertThat(commentInfoResponse.getCommentId()).isEqualTo(mockComment.getId());
        Assertions.assertThat(commentInfoResponse.getDescription()).isEqualTo(mockComment.getDescription());
        Assertions.assertThat(commentInfoResponse.getWriter()).isEqualTo(mockComment.getWriter().getNickname());
        Assertions.assertThat(commentInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockComment.getWriter().getProfileImageUrl());
    }

    @Test
    void 댓글을_조회한다_대댓글도_존재한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Comment mockParentComment = CommentTestUtils.getMockComment(mockUser, mockAlbum);
        Comment mockComment = CommentTestUtils.getChildMockComment(mockUser, mockAlbum, mockParentComment);
        List<Comment> mockCommentList = List.of(mockParentComment, mockComment);
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        Slice<Comment> mockPage = SliceTestUtils.getMockSlice(mockCommentList);
        given(commentQueryService.getCommentByAlbumId(mockPageable,mockAlbum.getId())).willReturn(mockPage);

        //when
        SliceResponse<CommentInfoResponse> commentList = commentReadUseCase.getCommentList(mockPageable, mockAlbum.getId());

        //then
        CommentInfoResponse commentInfoResponse = commentList.getContent().get(0);
        Assertions.assertThat(commentInfoResponse.getCommentId()).isEqualTo(mockParentComment.getId());
        Assertions.assertThat(commentInfoResponse.getDescription()).isEqualTo(mockParentComment.getDescription());
        Assertions.assertThat(commentInfoResponse.getWriter()).isEqualTo(mockParentComment.getWriter().getNickname());
        Assertions.assertThat(commentInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockParentComment.getWriter().getProfileImageUrl());
        CommentInfoResponse childCommentInfoResponse = commentList.getContent().get(0).getChild().get(0);
        Assertions.assertThat(childCommentInfoResponse.getCommentId()).isEqualTo(mockComment.getId());
        Assertions.assertThat(childCommentInfoResponse.getDescription()).isEqualTo(mockComment.getDescription());
        Assertions.assertThat(childCommentInfoResponse.getWriter()).isEqualTo(mockComment.getWriter().getNickname());
        Assertions.assertThat(childCommentInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockComment.getWriter().getProfileImageUrl());
    }
}
