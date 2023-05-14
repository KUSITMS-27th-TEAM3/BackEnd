package com.kusitms.samsion.domain.comment.presentation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.domain.comment.application.dto.request.CommentCreateRequest;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentUpdateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.service.CommentCreateUseCase;
import com.kusitms.samsion.domain.comment.application.service.CommentUpdateUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class CommentController {

    private final CommentCreateUseCase commentCreateUseCase;
    private final CommentUpdateUseCase commentUpdateUseCase;

    @PostMapping("/{albumId}/comment")
    public CommentInfoResponse createComment(@PathVariable Long albumId, CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createComment(albumId, commentCreateRequest);
    }

    @PostMapping("/{albumId}/comment/{commentId}")
    public CommentInfoResponse createReComment(@PathVariable Long albumId, @PathVariable Long commentId,
                                               CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createReComment(albumId, commentId, commentCreateRequest);
    }

    @PutMapping("/comment/{commentId}")
    public CommentInfoResponse update(@PathVariable Long commentId, CommentUpdateRequest commentUpdateRequest){
        return commentUpdateUseCase.updateComment(commentId, commentUpdateRequest);
    }
}
