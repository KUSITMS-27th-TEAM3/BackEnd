package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainService
@Transactional
@RequiredArgsConstructor
public class TagSaveService {

	private final TagRepository tagRepository;

	public void saveTagList(List<EmotionTag> emotionTagList, Album album){
		emotionTagList.forEach(emotionTag -> {
			Tag tag = new Tag(emotionTag, album);
			tagRepository.save(tag);
		});
	}
}
