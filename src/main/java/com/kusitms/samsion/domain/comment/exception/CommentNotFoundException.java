package com.kusitms.samsion.domain.comment.exception;

import com.kusitms.samsion.common.exception.Error;

public class CommentNotFoundException extends CommentException {
    public CommentNotFoundException(Error error) {
        super(error);
    }
}

