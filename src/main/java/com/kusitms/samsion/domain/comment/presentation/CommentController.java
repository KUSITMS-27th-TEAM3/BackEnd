package com.kusitms.samsion.domain.comment.presentation;

import com.kusitms.samsion.common.consts.CachingStoreConst;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentCreateRequest;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentCountResponse;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.service.CommentCreateUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentDeleteUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentReadUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class CommentController {

    private final CommentCreateUseCase commentCreateUseCase;
    private final CommentUpdateUseCase commentUpdateUseCase;
    private final CommentDeleteUseCase commentDeleteUseCase;
    private final CommentReadUseCase commentReadUseCase;

    @CacheEvict(value = CachingStoreConst.COMMENT_COUNT_CACHE_NAME, key = "#albumId")
    @PostMapping("/{albumId}/comment")
    public CommentInfoResponse createComment(@PathVariable Long albumId, @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createComment(albumId, commentCreateRequest);
    }

    @CacheEvict(value = CachingStoreConst.COMMENT_COUNT_CACHE_NAME, key = "#albumId")
    @PostMapping("/{albumId}/comment/{commentId}")
    public CommentInfoResponse createReComment(@PathVariable Long albumId, @PathVariable Long commentId,
                                               @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createReComment(albumId, commentId, commentCreateRequest);
    }

    @PutMapping("/{albumId}/comment/{commentId}")
    public CommentInfoResponse update(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentUpdateUseCase.updateComment(commentId, commentUpdateRequest);
    }

    @CacheEvict(value = CachingStoreConst.COMMENT_COUNT_CACHE_NAME, key = "#albumId")
    @DeleteMapping("/{albumId}/comment/{commentId}")
    public void delete(@PathVariable Long albumId,@PathVariable Long commentId){
        commentDeleteUseCase.deleteComment(commentId);
    }

    @GetMapping("/{albumId}/comment")
    public SliceResponse<CommentInfoResponse> getCommentList(Pageable pageable, @PathVariable Long albumId){
        return commentReadUseCase.getCommentList(pageable, albumId);
    }

    @GetMapping("/{albumId}/comment/count")
    public CommentCountResponse getCommentCount(@PathVariable Long albumId){
        return commentReadUseCase.getCommentCount(albumId);
    }
}
