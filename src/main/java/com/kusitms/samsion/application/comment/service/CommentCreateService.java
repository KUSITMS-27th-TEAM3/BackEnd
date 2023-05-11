package com.kusitms.samsion.application.comment.service;

import com.kusitms.samsion.application.comment.dto.request.CommentCreateRequest;
import com.kusitms.samsion.application.comment.dto.response.CommentInfoResponse;
import com.kusitms.samsion.application.comment.mapper.CommentMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.service.AlbumQueryService;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.comment.service.CommentQueryService;
import com.kusitms.samsion.domain.comment.service.CommentSaveService;
import com.kusitms.samsion.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
public class CommentCreateService {
    private final UserUtils userUtils;
    private final AlbumQueryService albumQueryService;
    private final CommentSaveService commentSaveService;
    private final CommentQueryService commentQueryService;

    @Transactional
    public CommentInfoResponse createComment(Long albumId, CommentCreateRequest commentCreateRequest){
        final User user = userUtils.getUser();
        final Album album = albumQueryService.getAlbumById(albumId);
        final Comment comment = CommentMapper.mapToCommentWithUserAndAlbum(commentCreateRequest.getDescription(), album, user);

        commentSaveService.saveComment(comment);
        return CommentMapper.mapToCommentInfoResponse(comment);
    }

    @Transactional
    public CommentInfoResponse createReComment(Long albumId, Long commentId, CommentCreateRequest commentCreateRequest){
        final User user = userUtils.getUser();
        final Album album = albumQueryService.getAlbumById(albumId);
        final Comment parent = commentQueryService.getCommentById(commentId);
        final Comment comment = CommentMapper.mapToCommentWithUserAndAlbumAndParent(commentCreateRequest.getDescription(), album, user, parent);

        commentSaveService.saveComment(comment);
        return CommentMapper.mapToCommentInfoResponse(comment);
    }
}
