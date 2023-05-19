package com.kusitms.samsion.domain.comment.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.exception.NotSameUserException;
import lombok.RequiredArgsConstructor;


@DomainService
@RequiredArgsConstructor
public class CommentValidAccessService {
    public void validateAccess(Comment comment, Long userId){
        if(!comment.getWriter().getId().equals(userId)){
            throw new NotSameUserException(Error.NOT_SAME_USER);
        }
    }
}
