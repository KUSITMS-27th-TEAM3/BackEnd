package com.kusitms.samsion.domain.enumdocs;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnumDocs {

	Map<String, String> emotionTagMap;
	Map<String, String> visibilityMap;
	Map<String, String> sortTypeMap;

	public EnumDocs(Map<String, String> emotionTagMap, Map<String, String> visibilityMap,
		Map<String, String> sortTypeMap) {
		this.emotionTagMap = emotionTagMap;
		this.visibilityMap = visibilityMap;
		this.sortTypeMap = sortTypeMap;
	}
}
