package com.kusitms.samsion.application.album.service;

import java.util.List;

import com.kusitms.samsion.infrastructure.s3.S3UploadService;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.application.album.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.application.album.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.application.album.mapper.AlbumImageMapper;
import com.kusitms.samsion.application.album.mapper.AlbumMapper;
import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.AlbumImage;
import com.kusitms.samsion.domain.album.service.AlbumImageSaveService;
import com.kusitms.samsion.domain.album.service.AlbumSaveService;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class AlbumCreateService {

	private final AlbumMapper albumMapper;
	private final AlbumImageMapper albumImageMapper;
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
		final Album album = albumMapper.mapToAlbumWithUser(albumCreateRequest, user);
		albumSaveService.saveAlbum(album);

		List<AlbumImage> albumImages = albumImageMapper.mapToAlbumImageListWithAlbum(imageUrls, album);
		albumImageSaveService.saveAlbumImageList(albumImages);
		return albumMapper.mapToAlbumInfoResponse(album);
	}
}
