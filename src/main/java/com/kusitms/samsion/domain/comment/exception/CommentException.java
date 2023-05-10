package com.kusitms.samsion.domain.comment.exception;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.Error;

public class CommentException extends BusinessException {
    public CommentException(Error error) {
        super(error);
    }
}
