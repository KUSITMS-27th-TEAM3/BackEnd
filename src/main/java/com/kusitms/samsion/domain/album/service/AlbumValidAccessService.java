package com.kusitms.samsion.domain.album.service;

import java.util.Objects;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.Visibility;
import com.kusitms.samsion.domain.album.exception.AlbumAccessDeniedException;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AlbumValidAccessService {

	public void validateAccess(Album album, Long userId){
		final Long albumUserId = album.getWriter().getId();
		if(album.getVisibility() == Visibility.PRIVATE && !Objects.equals(albumUserId,userId)){
			throw new AlbumAccessDeniedException(Error.ALBUM_ACCESS_DENIED);
		}
	}
}
