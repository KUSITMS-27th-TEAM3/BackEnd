package com.kusitms.samsion.domain.album.service;

import java.util.Objects;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.exception.AlbumAccessDeniedException;
import com.kusitms.samsion.domain.album.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AlbumValidAccessService {

	private final AlbumRepository albumRepository;

	public void validateAccess(Album album, Long userId){
		final Long albumUserId = album.getWriter().getId();
		if(!Objects.equals(albumUserId,userId)){
			throw new AlbumAccessDeniedException(Error.ALBUM_ACCESS_DENIED);
		}
	}
}
