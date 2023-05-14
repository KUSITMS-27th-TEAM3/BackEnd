package com.kusitms.samsion.domain.album.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.application.mapper.AlbumMapper;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.AlbumValidAccessService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@UserCase
@RequiredArgsConstructor
public class AlbumReadUserCase {

	private final AlbumQueryService albumQueryService;
	private final AlbumValidAccessService albumValidAccessService;
	private final UserUtils userUtils;

	/**
	 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함, 공개/비공개 처리해야함.
	 */
	public SliceResponse<AlbumSimpleResponse> getAlbumList(Pageable pageable){
		Slice<AlbumSimpleResponse> albumSimpleResponses =
			AlbumMapper.mapToAlbumSimpleResponse(albumQueryService.getAlbumList((pageable)));
		return SliceResponse.of(albumSimpleResponses);
	}

	public AlbumInfoResponse getAlbum(Long albumId){
		User user = userUtils.getUser();
		Album album = albumQueryService.getAlbumById(albumId);
		albumValidAccessService.validateAccess(album, user.getId());
		return AlbumMapper.mapToAlbumInfoResponse(album);
	}
}
