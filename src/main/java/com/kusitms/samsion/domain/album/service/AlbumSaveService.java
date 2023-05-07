package com.kusitms.samsion.domain.album.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AlbumSaveService {

	private final AlbumRepository albumRepository;

	public void saveAlbum(Album album) {

		albumRepository.save(album);
	}
}
