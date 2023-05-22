package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TagUpdateService {

	private final TagRepository tagRepository;

	public void updateTag(Album album, List<EmotionTag> emotionTagList){
		final List<Tag> tagList = emotionTagList.stream()
				.map(tag -> new Tag(tag, album))
				.collect(Collectors.toList());
		tagRepository.saveAll(tagList);
	}
}
