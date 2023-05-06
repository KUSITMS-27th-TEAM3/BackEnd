package com.kusitms.samsion.domain.album.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.exception.AlbumNotFoundException;
import com.kusitms.samsion.domain.album.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AlbumQueryService {

	private final AlbumRepository albumRepository;

	public Album getAlbumById(Long albumId){
		return albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(Error.ALBUM_NOT_FOUND));
	}

	public Slice<Album> getAlbumList(Pageable pageable){
		return albumRepository.findAllBy(pageable);
	}

}
