package com.kusitms.samsion.domain.album.application.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.mapper.AlbumMapper;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageSaveService;
import com.kusitms.samsion.domain.album.domain.service.AlbumSaveService;
import com.kusitms.samsion.domain.album.domain.service.TagSaveService;
import com.kusitms.samsion.domain.grid.application.dto.request.GridCountUpdateRequest;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class AlbumCreateUseCase {

	private final UserUtils userUtils;
	private final AlbumSaveService albumSaveService;
	private final AlbumImageSaveService albumImageSaveService;
	private final TagSaveService tagSaveService;
	private final S3UploadService s3UploadService;

	private final ApplicationEventPublisher applicationEventPublisher;

	/**
	 * TODO : 의존성이 너무 과한것같음, Album, AlbumImage, Tag을 같은 aggregate로 묶었지만 Album저장로직에 너무 많은 의존성이 생겨버림 이벤트 기반으로 변경할지고민중
	 */
	public AlbumInfoResponse createAlbum(AlbumCreateRequest albumCreateRequest){
		List<String> imageUrls = s3UploadService.uploadImgList(albumCreateRequest.getAlbumImages());

		final User user = userUtils.getUser();
		final Album album = AlbumMapper.mapToAlbumWithUser(albumCreateRequest, user);
		albumSaveService.saveAlbum(album);
		tagSaveService.saveTagList(albumCreateRequest.getEmotionTags(), album);
		albumImageSaveService.saveAlbumImageList(imageUrls, album);

		applicationEventPublisher.publishEvent(new GridCountUpdateRequest(user.getId()));

		return AlbumMapper.mapToAlbumInfoResponse(album, 0, 0, albumCreateRequest.getEmotionTags(), user);
	}
}
