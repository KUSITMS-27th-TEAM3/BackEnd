package com.kusitms.samsion.domain.comment.application.service;

import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.mapper.CommentMapper;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import lombok.RequiredArgsConstructor;

@UserCase
@RequiredArgsConstructor
public class CommentUpdateUseCase {
    private final CommentQueryService commentQueryService;
    public CommentInfoResponse updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        final Comment comment = commentQueryService.getCommentById(commentId);
        comment.updateDescription(commentUpdateRequest.getDescription());
        return CommentMapper.mapToCommentInfoResponse(comment);
    }
}
