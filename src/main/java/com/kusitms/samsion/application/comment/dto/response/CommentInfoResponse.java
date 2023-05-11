package com.kusitms.samsion.application.comment.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentInfoResponse {

    private final String description;
    private final String writer;
    private final String writerProfileImageUrl;

    @Builder
    public CommentInfoResponse(String description, String writer, String writerProfileImageUrl) {
        this.description = description;
        this.writer = writer;
        this.writerProfileImageUrl = writerProfileImageUrl;
    }
}
