package com.kusitms.samsion.domain.comment.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.comment.application.dto.request.CommentCreateRequest;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.mapper.CommentMapper;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.comment.domain.service.CommentSaveService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CommentCreateUseCase {
    private final UserUtils userUtils;
    private final AlbumQueryService albumQueryService;
    private final CommentSaveService commentSaveService;
    private final CommentQueryService commentQueryService;

    @Transactional
    public CommentInfoResponse createComment(Long albumId, CommentCreateRequest commentCreateRequest){
        final User user = userUtils.getUser();
        final Album album = albumQueryService.findAlbumById(albumId);
        final Comment comment = CommentMapper.mapToCommentWithUserAndAlbum(commentCreateRequest.getDescription(), album, user);

        commentSaveService.saveComment(comment);
        return CommentMapper.mapToCommentInfoResponse(comment);
    }

    @Transactional
    public CommentInfoResponse createReComment(Long albumId, Long commentId, CommentCreateRequest commentCreateRequest){
        final User user = userUtils.getUser();
        final Album album = albumQueryService.findAlbumById(albumId);
        final Comment parent = commentQueryService.getCommentById(commentId);
        final Comment comment = CommentMapper.mapToCommentWithUserAndAlbumAndParent(commentCreateRequest.getDescription(), album, user, parent);

        commentSaveService.saveComment(comment);
        return CommentMapper.mapToCommentInfoResponse(comment);
    }
}
