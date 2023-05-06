package com.kusitms.samsion.application.album.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

	@Transactional
	public AlbumInfoResponse createAlbum(List<MultipartFile> multipartFiles, AlbumCreateRequest albumCreateRequest){
		// TODO : 이미지 업로드
		List<String> imageUrls = null;
		// TODO : 앨범 생성
		final User user = userUtils.getUser();
		final Album album = albumMapper.mapToAlbumWithUser(albumCreateRequest, user);
		albumSaveService.saveAlbum(album);

		List<AlbumImage> albumImages = albumImageMapper.mapToAlbumImageListWithAlbum(imageUrls, album);
		albumImageSaveService.saveAlbumImageList(albumImages);
		return albumMapper.mapToAlbumInfoResponse(album);
	}
}
