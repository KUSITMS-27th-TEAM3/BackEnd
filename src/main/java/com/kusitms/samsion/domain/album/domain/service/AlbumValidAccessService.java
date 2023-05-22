package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;
import com.kusitms.samsion.domain.album.domain.exception.AlbumAccessDeniedException;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@DomainService
@RequiredArgsConstructor
public class AlbumValidAccessService {

	public void validateAccess(Album album, Long userId){
		final Long albumUserId = album.getWriter().getId();
		if(Objects.equals(album.getVisibility(), Visibility.PRIVATE) && !Objects.equals(albumUserId,userId)){
			throw new AlbumAccessDeniedException(Error.ALBUM_ACCESS_DENIED);
		}
	}
}
