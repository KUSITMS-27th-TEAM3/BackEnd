package com.kusitms.samsion.domain.album.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.repository.AlbumImageRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
@Transactional
public class AlbumImageUpdateService {

	private final AlbumImageRepository albumImageRepository;

	public void updateAlbumImage(Album album, List<AlbumImage> albumImageList){
		album.changeAllImage(albumImageList);
		albumImageRepository.saveAll(albumImageList);
	}
}
