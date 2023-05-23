package com.kusitms.samsion.domain.comment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentInfoResponse {
    private final Long commentId;
    private final String description;
    private final String writer;
    private final String writerProfileImageUrl;
    private final boolean deleted;
    private final boolean changeable;
    private List<CommentInfoResponse> child;

    @Builder
    public CommentInfoResponse(Long commentId, String description, String writer, String writerProfileImageUrl, boolean deleted, boolean changeable) {
        this.commentId = commentId;
        this.description = description;
        this.writer = writer;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.deleted = deleted;
        this.changeable = changeable;
    }
}
