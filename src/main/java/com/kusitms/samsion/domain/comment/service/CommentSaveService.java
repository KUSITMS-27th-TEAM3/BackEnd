package com.kusitms.samsion.domain.comment.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CommentSaveService {

    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {

        commentRepository.save(comment);
    }
}
