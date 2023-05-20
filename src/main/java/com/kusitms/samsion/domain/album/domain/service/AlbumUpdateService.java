package com.kusitms.samsion.domain.album.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class AlbumUpdateService {

	public void updateAlbum(Album album, String title, String description, Visibility visibility) {
		album.updateAlbum(title, description, visibility);
	}
}
