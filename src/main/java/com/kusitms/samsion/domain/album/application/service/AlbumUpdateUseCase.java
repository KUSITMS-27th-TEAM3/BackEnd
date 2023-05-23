package com.kusitms.samsion.domain.album.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumImageUpdateRequest;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumUpdateRequest;
import com.kusitms.samsion.domain.album.application.dto.request.TagUpdateRequest;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.*;
import com.kusitms.samsion.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class AlbumUpdateUseCase {

	private final UserUtils userUtils;
	private final AlbumQueryService albumQueryService;
	private final AlbumValidAccessService albumValidAccessService;
	private final AlbumUpdateService albumUpdateService;
	private final AlbumImageDeleteService albumImageDeleteService;
	private final TagDeleteService tagDeleteService;

	private final ApplicationEventPublisher applicationEventPublisher;

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
