package com.kusitms.samsion.common.util;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.user.entity.User;
import org.springframework.test.util.ReflectionTestUtils;

public class CommentTestUtils {

    public static Comment getMockComment(User mockUser, Album mockAlbum) {
        Comment mockComment = Comment.builder()
                .description("comment description")
                .album(mockAlbum)
                .writer(mockUser)
                .build();
        ReflectionTestUtils.setField(mockComment, "id", TestConst.TEST_ID);
        return mockComment;
    }
}
