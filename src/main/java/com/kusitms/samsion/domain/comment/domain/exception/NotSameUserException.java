package com.kusitms.samsion.domain.comment.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class NotSameUserException extends CommentException {
    public NotSameUserException(Error error) {
        super(error);
    }
}


