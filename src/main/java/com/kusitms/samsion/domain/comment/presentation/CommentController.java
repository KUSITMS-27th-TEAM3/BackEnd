package com.kusitms.samsion.domain.comment.presentation;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.common.consts.CachingStoreConst;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentCreateRequest;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.service.CommentCreateUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentDeleteUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentReadUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentUpdateUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class CommentController {

    private final CommentCreateUseCase commentCreateUseCase;
    private final CommentUpdateUseCase commentUpdateUseCase;
    private final CommentDeleteUseCase commentDeleteUseCase;
    private final CommentReadUseCase commentReadUseCase;

    @PostMapping("/{albumId}/comment")
    public CommentInfoResponse createComment(@PathVariable Long albumId, @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createComment(albumId, commentCreateRequest);
    }

    @PostMapping("/{albumId}/comment/{commentId}")
    public CommentInfoResponse createReComment(@PathVariable Long albumId, @PathVariable Long commentId,
                                               @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createReComment(albumId, commentId, commentCreateRequest);
    }

    @CacheEvict(value = CachingStoreConst.COMMENT_COUNT_CACHE_NAME, key = "#comment.albumId")
    @PutMapping("/comment/{commentId}")
    public CommentInfoResponse update(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentUpdateUseCase.updateComment(commentId, commentUpdateRequest);
    }

    @CacheEvict(value = CachingStoreConst.COMMENT_COUNT_CACHE_NAME, key = "#comment.albumId")
    @DeleteMapping("/comment/{commentId}")
    public void delete(@PathVariable Long commentId){
        commentDeleteUseCase.deleteComment(commentId);
    }

    @GetMapping("/{albumId}/comment")
    public SliceResponse<CommentInfoResponse> getCommentList(Pageable pageable, @PathVariable Long albumId){
        return commentReadUseCase.getCommentList(pageable, albumId);
    }
}
