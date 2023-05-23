package com.kusitms.samsion.domain.comment.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentCountResponse;
import com.kusitms.samsion.domain.comment.application.dto.response.CommentInfoResponse;
import com.kusitms.samsion.domain.comment.application.mapper.CommentMapper;
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@UseCase
@RequiredArgsConstructor
public class CommentReadUseCase {

    private final UserUtils userUtils;
    private final CommentQueryService commentQueryService;

    public SliceResponse<CommentInfoResponse> getCommentList(Pageable pageable, Long albumId){

        Slice<Comment> commentList = commentQueryService.getCommentByAlbumId(pageable,albumId);

        return getSliceResponseAboutCommentInfoResponse(commentList);
    }

    private SliceResponse<CommentInfoResponse> getSliceResponseAboutCommentInfoResponse(Slice<Comment> commentList) {
        Map<Long, CommentInfoResponse> commentInfoResponseMap = new HashMap<>();
        List<CommentInfoResponse> commentInfoResponseList = new ArrayList<>();

        final User user = userUtils.getUser();

        commentList.forEach(comment -> {
            CommentInfoResponse commentInfoResponse = CommentMapper.mapToCommentInfoResponse(comment, user);
            commentInfoResponseMap.put(commentInfoResponse.getCommentId(), commentInfoResponse);
            if (comment.getParent() != null) {
                CommentInfoResponse parentInfoResponse = commentInfoResponseMap.get(comment.getParent().getId());
                parentInfoResponse.setChild(new ArrayList<>());
                parentInfoResponse.getChild().add(commentInfoResponse);
            } else {
                commentInfoResponseList.add(commentInfoResponse);
            }
        });

        Slice<CommentInfoResponse> commentInfoResponses = new SliceImpl<>(commentInfoResponseList, commentList.getPageable(), commentList.hasNext());
        return SliceResponse.of(commentInfoResponses);
    }

    public CommentCountResponse getCommentCount(final Long albumId) {
        final long commentCount = commentQueryService.getCommentCountByAlbumId(albumId);
        return CommentMapper.mapToCommentCountResponse(commentCount);
    }
}
