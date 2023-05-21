package com.kusitms.samsion.domain.album.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumDeleteService;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.AlbumValidAccessService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class AlbumDeleteUseCase {

	private final UserUtils userUtils;
	private final AlbumQueryService albumQueryService;
	private final AlbumValidAccessService albumValidAccessService;
	private final AlbumDeleteService albumDeleteService;

	public void deleteAlbum(Long albumId) {
		final User user = userUtils.getUser();
		final Album album = albumQueryService.findAlbumById(albumId);
		albumValidAccessService.validateAccess(album, user.getId());
		albumDeleteService.deleteAlbum(albumId);
	}
}
