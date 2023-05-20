package com.kusitms.samsion.domain.comment.domain.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.consts.CachingStoreConst;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.exception.CommentNotFoundException;
import com.kusitms.samsion.domain.comment.domain.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentRepository commentRepository;

    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(Error.COMMENT_NOT_FOUND));
    }

    public Slice<Comment> getCommentByAlbumId(Pageable pageable, Long albumId) {
        return commentRepository.findByAlbumId(pageable, albumId);
    }

    @Cacheable(value = CachingStoreConst.COMMENT_COUNT_CACHE_NAME, key = "#albumId")
    public long getCommentCountByAlbumId(Long albumId){
        return commentRepository.countByAlbumId(albumId);
    }

}
