package com.kusitms.samsion.domain.comment.application.dto.response;

import lombok.Getter;

@Getter
public class CommentCountResponse {

    private final long commentCount;

    public CommentCountResponse(long commentCount) {
        this.commentCount = commentCount;
    }
}
