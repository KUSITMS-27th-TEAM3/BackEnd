package com.kusitms.samsion.domain.comment.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.comment.domain.service.CommentValidAccessService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CommentDeleteUseCase {

    private final UserUtils userUtils;
    private final CommentQueryService commentQueryService;
    private final CommentValidAccessService commentValidAccessService;

    public void deleteComment(Long commentId) {
        final User user = userUtils.getUser();
        final Comment comment = commentQueryService.getCommentById(commentId);
        commentValidAccessService.validateAccess(comment, user.getId());
        comment.remove();
    }

}
