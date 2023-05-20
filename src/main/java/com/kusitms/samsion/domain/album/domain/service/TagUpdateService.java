package com.kusitms.samsion.domain.album.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TagUpdateService {

	private final TagRepository tagRepository;

	public void updateTag(Album album, List<Tag> tagList){
		album.changeAllTag(tagList);
		tagRepository.saveAll(tagList);
	}
}
