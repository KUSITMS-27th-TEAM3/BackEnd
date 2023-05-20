package com.kusitms.samsion.domain.comment.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CommentSaveService {

    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {

        commentRepository.save(comment);
    }
}
