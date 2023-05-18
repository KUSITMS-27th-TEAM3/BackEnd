package com.kusitms.samsion.domain.comment.domain.service;

import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.CommentTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.repository.CommentRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentDeleteService 테스트")
public class CommentDeleteServiceTest {
    @Mock
    private CommentRepository commentRepository;

    CommentDeleteService commentDeleteService;

    @BeforeEach
    void setUp() {
        commentDeleteService = new CommentDeleteService(commentRepository);
    }

    @Test
    void 댓글_삭제_요청을_받는다(){
        //given
        final User mockUser = UserTestUtils.getMockUser();
        final Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        final Comment mockComment = CommentTestUtils.getMockComment(mockUser,mockAlbum);
        //when
        commentDeleteService.deleteComment(mockComment);
        //then
        then(commentRepository).should(times(1)).deleteById(mockComment.getId());
    }
}
