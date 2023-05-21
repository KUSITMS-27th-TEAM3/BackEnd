package com.kusitms.samsion.domain.album.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class AlbumDeleteService {

	private final AlbumRepository albumRepository;

	public void deleteAlbum(Long albumId) {
		albumRepository.deleteById(albumId);
	}
}
