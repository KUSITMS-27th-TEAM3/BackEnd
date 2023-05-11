package com.kusitms.samsion.domain.comment.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.CommentTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.Visibility;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.comment.exception.CommentNotFoundException;
import com.kusitms.samsion.domain.comment.repository.CommentRepository;
import com.kusitms.samsion.domain.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentQueryService 테스트")
public class CommentQueryServiceTest {

    @Mock
    private CommentRepository commentRepository;

    CommentQueryService commentQueryService;

    @BeforeEach
    void setUp() {
        commentQueryService = new CommentQueryService(commentRepository);
    }

    @Test
    void 댓글_조회_요청을_받는다(){
        //given
        final User mockUser = UserTestUtils.getMockUser();
        final Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        final Comment mockComment = CommentTestUtils.getMockComment(mockUser, mockAlbum);
        given(commentRepository.findById(mockAlbum.getId())).willReturn(Optional.of(mockComment));
        //when
        Comment comment = commentQueryService.getCommentById(mockComment.getId());
        //then
        Assertions.assertThat(comment).isNotNull();
        Assertions.assertThat(comment).usingRecursiveComparison().isEqualTo(mockComment);
        then(commentRepository).should(times(1)).findById(mockComment.getId());
    }

    @Test
    void 댓글_조회시_댓글이_없는_경우_예외가_발생한다(){
        //given
        given(commentRepository.findById(TestConst.TEST_ID)).willReturn(Optional.empty());
        //when
        //then
        Assertions.assertThatThrownBy(() -> commentQueryService.getCommentById(TestConst.TEST_ID))
                .isInstanceOf(CommentNotFoundException.class);
    }

}
