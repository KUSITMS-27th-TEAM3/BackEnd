package com.kusitms.samsion.domain.album.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.domain.album.application.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.mapper.AlbumMapper;
import com.kusitms.samsion.common.annotation.UserCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageSaveService;
import com.kusitms.samsion.domain.album.domain.service.AlbumSaveService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;

import lombok.RequiredArgsConstructor;

@UserCase
@RequiredArgsConstructor
public class AlbumCreateUserCase {

	private final AlbumSaveService albumSaveService;
	private final AlbumImageSaveService albumImageSaveService;
	private final UserUtils userUtils;
	private final S3UploadService s3UploadService;

	/**
	 * TODO : 리팩터링 해야함
	 */
	@Transactional
	public AlbumInfoResponse createAlbum(AlbumCreateRequest albumCreateRequest){
		List<String> imageUrls = s3UploadService.uploadImgList(albumCreateRequest.getImages());

		final User user = userUtils.getUser();
		final Album album = AlbumMapper.mapToAlbumWithUser(albumCreateRequest, user);
		albumSaveService.saveAlbum(album);

		albumImageSaveService.saveAlbumImageList(imageUrls, album);
		return AlbumMapper.mapToAlbumInfoResponse(album);
	}
}
