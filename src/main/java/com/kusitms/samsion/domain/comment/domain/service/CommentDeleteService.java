package com.kusitms.samsion.domain.comment.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CommentDeleteService {

    private final CommentRepository commentRepository;

    public void deleteComment(Comment comment) {

        commentRepository.deleteById(comment.getId());
    }

}
