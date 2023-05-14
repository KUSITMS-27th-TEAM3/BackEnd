package com.kusitms.samsion.domain.comment.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private String description;

    @Builder
    public CommentCreateRequest(String description) {
        this.description = description;
    }
}
