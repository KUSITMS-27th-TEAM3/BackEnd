package com.kusitms.samsion.presentation.comment;

import com.kusitms.samsion.application.comment.dto.request.CommentCreateRequest;
import com.kusitms.samsion.application.comment.dto.response.CommentInfoResponse;
import com.kusitms.samsion.application.comment.service.CommentCreateService;
import com.kusitms.samsion.application.comment.service.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class CommentController {

    private final CommentCreateService commentCreateService;
    private final CommentUpdateService commentUpdateService;

    @PostMapping("/{albumId}/comment")
    public CommentInfoResponse createComment(@PathVariable Long albumId, CommentCreateRequest commentCreateRequest) {
        return commentCreateService.createComment(albumId, commentCreateRequest.getDescription());
    }

    @PostMapping("/{albumId}/comment/{commentId}")
    public CommentInfoResponse createReComment(@PathVariable Long albumId, @PathVariable Long commentId,
                                               CommentCreateRequest commentCreateRequest) {
        return commentCreateService.createReComment(albumId, commentId, commentCreateRequest.getDescription());
    }

    @PutMapping("/comment/{commentId}")
    public CommentInfoResponse update(@PathVariable Long commentId, CommentCreateRequest commentCreateRequest){
        return commentUpdateService.updateComment(commentId, commentCreateRequest.getDescription());
    }
}
