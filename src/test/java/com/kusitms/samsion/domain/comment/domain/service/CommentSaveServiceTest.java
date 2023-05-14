package com.kusitms.samsion.domain.comment.domain.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.CommentTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.repository.CommentRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentSaveService 테스트")
public class CommentSaveServiceTest {

    @Mock
    private CommentRepository commentRepository;

    CommentSaveService commentSaveService;

    @BeforeEach
    void setUp() {
        commentSaveService = new CommentSaveService(commentRepository);
    }

    @Test
    void 댓글_저장_요청을_받는다(){
        //given
        final User mockUser = UserTestUtils.getMockUser();
        final Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        final Comment mockComment = CommentTestUtils.getMockComment(mockUser, mockAlbum);
        //when
        commentSaveService.saveComment(mockComment);
        //then
        then(commentRepository).should(times(1)).save(any(Comment.class));
    }
}
