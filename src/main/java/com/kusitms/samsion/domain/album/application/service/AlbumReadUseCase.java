package com.kusitms.samsion.domain.album.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumSearchRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.application.mapper.AlbumMapper;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.AlbumValidAccessService;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.empathy.domain.service.EmpathyQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@UseCase
@RequiredArgsConstructor
public class AlbumReadUseCase {

	private final UserUtils userUtils;
	private final AlbumQueryService albumQueryService;
	private final AlbumValidAccessService albumValidAccessService;

	private final CommentQueryService commentQueryService;
	private final EmpathyQueryService empathyQueryService;

	/**
	 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함, 공개/비공개 처리해야함.
	 */
	public SliceResponse<AlbumSimpleResponse> getAlbumList(Pageable pageable, AlbumSearchRequest request){

		Slice<Album> albumList = albumQueryService.findAlbumList(pageable,request.getEmotionTagList(), request.getSortType());

		return getSliceResponseAboutAlbumSimpleResponse(albumList);
	}

	public SliceResponse<AlbumSimpleResponse> getMyAlbumList(Pageable pageable, AlbumSearchRequest request){
		Slice<Album> myAlbumList =
			albumQueryService.findMyAlbumList(pageable, request.getEmotionTagList(), request.getSortType(), userUtils.getUser().getId());

		return getSliceResponseAboutAlbumSimpleResponse(myAlbumList);
	}

	public AlbumInfoResponse getAlbum(Long albumId){
		User user = userUtils.getUser();
		Album album = albumQueryService.findAlbumById(albumId);
		final List<EmotionTag> emotionTagList  = album.getTags().stream().map(Tag::getEmotionTag).collect(Collectors.toList());
		albumValidAccessService.validateAccess(album, user.getId());
		return AlbumMapper.mapToAlbumInfoResponse(album, emotionTagList, user);
	}

	/**
	 * DTO로 변환하는 과정에서 추가적인 쿼리가 필요하기 때문에 mapper가 아닌 usecase에서 처리
	 */
	private SliceResponse<AlbumSimpleResponse> getSliceResponseAboutAlbumSimpleResponse(Slice<Album> albumList) {
		Slice<AlbumSimpleResponse> albumSimpleResponses = albumList.map(album -> {
			final long commentCountByAlbumId = commentQueryService.getCommentCountByAlbumId(album.getId());
			final long empathyCountByAlbumId = empathyQueryService.getEmpathyCountByAlbumId(album.getId());
			return AlbumMapper.mapToAlbumSimpleResponse(album, commentCountByAlbumId, empathyCountByAlbumId);
		});
		return SliceResponse.of(albumSimpleResponses);
	}
}
