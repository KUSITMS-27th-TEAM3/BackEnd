package com.kusitms.samsion.domain.album.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.repository.AlbumImageRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
@Transactional
public class AlbumImageDeleteService {

	private final AlbumImageRepository albumImageRepository;

	public void deleteAlbumImageList(Album album){
		album.clearAllImage();
		albumImageRepository.deleteAllByAlbumId(album.getId());
	}
}
