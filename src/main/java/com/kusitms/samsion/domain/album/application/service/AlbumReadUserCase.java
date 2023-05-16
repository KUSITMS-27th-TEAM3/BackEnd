package com.kusitms.samsion.domain.album.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumSearchRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.application.mapper.AlbumMapper;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.AlbumValidAccessService;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.empathy.domain.service.EmpathyQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@UseCase
@RequiredArgsConstructor
public class AlbumReadUserCase {

	private final UserUtils userUtils;
	private final AlbumQueryService albumQueryService;
	private final AlbumValidAccessService albumValidAccessService;

	private final CommentQueryService commentQueryService;
	private final EmpathyQueryService empathyQueryService;

	/**
	 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함, 공개/비공개 처리해야함.
	 */
	public SliceResponse<AlbumSimpleResponse> getAlbumList(Pageable pageable, AlbumSearchRequest request){

		Slice<Album> albumList = albumQueryService.getAlbumList(pageable,request.getEmotionTagList(), request.getSortType());

		Slice<AlbumSimpleResponse> albumSimpleResponses = albumList.map(album -> {
			final long commentCountByAlbumId = commentQueryService.getCommentCountByAlbumId(album.getId());
			final long empathyCountByAlbumId = empathyQueryService.getEmpathyCountByAlbumId(album.getId());
			return AlbumMapper.mapToAlbumSimpleResponse(album, commentCountByAlbumId, empathyCountByAlbumId);
		});
		return SliceResponse.of(albumSimpleResponses);
	}

	public AlbumInfoResponse getAlbum(Long albumId){
		User user = userUtils.getUser();
		Album album = albumQueryService.getAlbumById(albumId);
		albumValidAccessService.validateAccess(album, user.getId());
		return AlbumMapper.mapToAlbumInfoResponse(album);
	}
}
