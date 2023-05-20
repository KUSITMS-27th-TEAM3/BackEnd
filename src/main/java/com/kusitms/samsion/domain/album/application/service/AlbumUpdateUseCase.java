package com.kusitms.samsion.domain.album.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumImageUpdateRequest;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumUpdateRequest;
import com.kusitms.samsion.domain.album.application.dto.request.TagUpdateRequest;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageDeleteService;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageUpdateService;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.AlbumUpdateService;
import com.kusitms.samsion.domain.album.domain.service.AlbumValidAccessService;
import com.kusitms.samsion.domain.album.domain.service.TagDeleteService;
import com.kusitms.samsion.domain.album.domain.service.TagUpdateService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class AlbumUpdateUseCase {

	private final UserUtils userUtils;
	private final AlbumQueryService albumQueryService;
	private final AlbumValidAccessService albumValidAccessService;
	private final AlbumUpdateService albumUpdateService;
	private final S3UploadService s3UploadService;
	private final AlbumImageUpdateService albumImageUpdateService;
	private final AlbumImageDeleteService albumImageDeleteService;
	private final TagUpdateService tagUpdateService;
	private final TagDeleteService tagDeleteService;

	private final ApplicationEventPublisher applicationEventPublisher;

	/**
	 * delete
	 */
	public void updateAlbum(final Long albumId,final AlbumUpdateRequest request){
		final User user = userUtils.getUser();
		final Album album = albumQueryService.findAlbumById(albumId);
		albumValidAccessService.validateAccess(album, user.getId());

		albumUpdateService.updateAlbum(album, request.getTitle(), request.getDescription(), request.getVisibility());

		albumImageDeleteService.deleteAlbumImageList(album);
		tagDeleteService.deleteTagList(album);

		applicationEventPublisher.publishEvent(new AlbumImageUpdateRequest(album.getId(), request.getImageUrlList(),request.getAddImageList()));
		applicationEventPublisher.publishEvent(new TagUpdateRequest(album.getId(), request.getEmotionTagList()));
	}
}