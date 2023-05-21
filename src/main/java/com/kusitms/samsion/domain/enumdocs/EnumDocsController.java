package com.kusitms.samsion.domain.enumdocs;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.SortType;
import com.kusitms.samsion.domain.album.domain.entity.Visibility;

@RestController
@RequestMapping("/enumdocs")
public class EnumDocsController {

	@GetMapping
	public EnumDocs getEnumDocs() {
		Map<String, String> emotionTagMap = getEmotionTagMap();
		Map<String, String> visibilityMap = getVisibilityMap();
		Map<String, String> sortTypeMap = getSortTypeMap();

		return new EnumDocs(emotionTagMap, visibilityMap, sortTypeMap);
	}

	private Map<String ,String> getEmotionTagMap() {
		return Arrays.stream(EmotionTag.values())
			.collect((Collectors.toMap(EmotionTag::name, EmotionTag::getDescription)));
	}

	private Map<String ,String> getVisibilityMap() {
		return Arrays.stream(Visibility.values())
			.collect((Collectors.toMap(Visibility::name, Visibility::getDescription)));
	}

	private Map<String ,String> getSortTypeMap() {
		return Arrays.stream(SortType.values())
			.collect((Collectors.toMap(SortType::name, SortType::getDescription)));
	}
}
