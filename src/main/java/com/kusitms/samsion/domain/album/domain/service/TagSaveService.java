package com.kusitms.samsion.domain.album.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class TagSaveService {

	private final TagRepository tagRepository;

	public void saveTag(EmotionTag emotionTag, Album album) {
		Tag tag = new Tag(emotionTag, album);
		tagRepository.save(tag);
	}

	public void saveTagList(List<EmotionTag> emotionTagList, Album album){
		emotionTagList.forEach(emotionTag -> {
			Tag tag = new Tag(emotionTag, album);
			tagRepository.save(tag);
		});
	}
}
