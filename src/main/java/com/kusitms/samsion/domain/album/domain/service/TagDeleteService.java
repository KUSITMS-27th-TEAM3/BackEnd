package com.kusitms.samsion.domain.album.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TagDeleteService {

	private final TagRepository tagRepository;

	public void deleteTagList(Album album){
		album.clearAllTag();
		tagRepository.deleteAllByAlbumId(album.getId());
	}
}
