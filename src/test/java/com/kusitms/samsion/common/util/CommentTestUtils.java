package com.kusitms.samsion.common.util;

import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.user.domain.entity.User;

public class CommentTestUtils {

    public static Comment getMockComment(User mockUser, Album mockAlbum) {
        Comment mockComment = Comment.builder()
                .description(TestConst.TEST_COMMENT_DESCRIPTION)
                .album(mockAlbum)
                .writer(mockUser)
                .build();
        ReflectionTestUtils.setField(mockComment, "id", TestConst.TEST_COMMENT_ID);
        return mockComment;
    }

    public static Comment getChildMockComment(User mockUser, Album mockAlbum, Comment mockParentComment) {
        Comment mockComment = Comment.builder()
                .description(TestConst.TEST_CHILD_COMMENT_DESCRIPTION)
                .album(mockAlbum)
                .writer(mockUser)
                .parent(mockParentComment)
                .build();
        ReflectionTestUtils.setField(mockComment, "id", TestConst.TEST_CHILD_ID);
        return mockComment;
    }
}
