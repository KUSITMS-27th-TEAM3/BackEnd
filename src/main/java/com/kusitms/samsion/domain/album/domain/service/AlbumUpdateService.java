package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class AlbumUpdateService {

	public void updateAlbum(Album album, String title, String description, Visibility visibility) {
		album.updateTitle(title);
		album.updateDescription(description);
		album.updateVisibility(visibility);
	}
}
