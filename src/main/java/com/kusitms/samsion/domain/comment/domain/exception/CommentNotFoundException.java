package com.kusitms.samsion.domain.comment.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class CommentNotFoundException extends CommentException {
    public CommentNotFoundException(Error error) {
        super(error);
    }
}

