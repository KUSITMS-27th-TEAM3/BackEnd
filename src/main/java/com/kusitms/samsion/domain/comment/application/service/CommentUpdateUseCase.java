package com.kusitms.samsion.domain.comment.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.mapper.CommentMapper;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.comment.domain.service.CommentSaveService;
import com.kusitms.samsion.domain.comment.domain.service.CommentValidAccessService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
public class CommentUpdateUseCase {
    private final UserUtils userUtils;
    private final CommentQueryService commentQueryService;
    private final CommentValidAccessService commentValidAccessService;
    @Transactional
    public CommentInfoResponse updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        final User user = userUtils.getUser();
        final Comment comment = commentQueryService.getCommentById(commentId);
        commentValidAccessService.validateAccess(comment, user.getId());
        comment.updateDescription(commentUpdateRequest.getDescription());
        return CommentMapper.mapToCommentInfoResponse(comment, user);
    }
}
