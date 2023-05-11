package com.kusitms.samsion.application.comment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private final String description;

    @Builder
    public CommentCreateRequest(String description) {
        this.description = description;
    }
}
