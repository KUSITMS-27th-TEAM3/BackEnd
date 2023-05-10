package com.kusitms.samsion.domain.comment.service;

import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.Visibility;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.comment.repository.CommentRepository;
import com.kusitms.samsion.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

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
        final Album mockAlbum = getMockAlbum(mockUser);
        final Comment mockComment = getMockComment(mockUser, mockAlbum);
        //when
        commentSaveService.saveComment(mockComment);
        //then
        then(commentRepository).should(times(1)).save(any(Comment.class));
    }

    private Comment getMockComment(User mockUser, Album mockAlbum) {
        Comment mockComment = Comment.builder()
                .description("comment description")
                .album(mockAlbum)
                .writer(mockUser)
                .build();
        ReflectionTestUtils.setField(mockComment, "id", 1L);
        return mockComment;
    }

    private Album getMockAlbum(User mockUser) {
        Album mockAlbum = Album.builder()
                .writer(mockUser)
                .visibility(Visibility.PUBLIC)
                .description("album description")
                .build();
        ReflectionTestUtils.setField(mockAlbum, "id", 1L);
        return mockAlbum;
    }
}
