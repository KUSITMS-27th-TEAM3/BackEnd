package com.kusitms.samsion.common.util;

import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.user.domain.entity.User;

public class CommentTestUtils {

    public static Comment getMockComment(User mockUser, Album mockAlbum) {
        Comment mockComment = Comment.builder()
                .description("comment description")
                .album(mockAlbum)
                .writer(mockUser)
                .build();
        ReflectionTestUtils.setField(mockComment, "id", TestConst.TEST_COMMENT_ID);
        return mockComment;
    }
}
