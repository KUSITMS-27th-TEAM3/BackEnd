package com.kusitms.samsion.domain.album.domain.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.album.domain.entity.SortType;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.exception.AlbumNotFoundException;
import com.kusitms.samsion.domain.album.domain.repository.AlbumRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class AlbumQueryService {

	private final AlbumRepository albumRepository;

	public Album getAlbumById(Long albumId){
		return albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(Error.ALBUM_NOT_FOUND));
	}

	public Slice<Album> getAlbumList(Pageable pageable, List<EmotionTag> emotionTagList, SortType sortType){
		return albumRepository.findAlbumList(pageable, emotionTagList, sortType);
	}

}
