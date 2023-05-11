package com.kusitms.samsion.domain.comment.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.comment.exception.CommentNotFoundException;
import com.kusitms.samsion.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentRepository commentRepository;

    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(Error.COMMENT_NOT_FOUND));
    }
}
