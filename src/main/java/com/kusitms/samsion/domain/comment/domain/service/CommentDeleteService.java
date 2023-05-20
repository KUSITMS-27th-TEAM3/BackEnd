package com.kusitms.samsion.domain.comment.domain.service;

import org.springframework.cache.annotation.CacheEvict;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.consts.CachingStoreConst;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CommentDeleteService {

    private final CommentRepository commentRepository;

    @CacheEvict(value = CachingStoreConst.COMMENT_COUNT_CACHE_NAME, key = "#comment.albumId")
    public void deleteComment(Comment comment) {

        commentRepository.deleteById(comment.getId());
    }

}
