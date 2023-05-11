package com.kusitms.samsion.application.comment.service;

import com.kusitms.samsion.application.comment.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.application.comment.dto.response.CommentInfoResponse;
import com.kusitms.samsion.application.comment.mapper.CommentMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.comment.service.CommentQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class CommentUpdateService {
    private final CommentQueryService commentQueryService;
    public CommentInfoResponse updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        final Comment comment = commentQueryService.getCommentById(commentId);
        comment.updateDescription(commentUpdateRequest.getDescription());
        return CommentMapper.mapToCommentInfoResponse(comment);
    }
}
