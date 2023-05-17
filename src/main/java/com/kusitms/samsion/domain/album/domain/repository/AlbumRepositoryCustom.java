package com.kusitms.samsion.domain.album.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.SortType;

public interface AlbumRepositoryCustom {

	Slice<Album> findAlbumList(Pageable pageable, List<EmotionTag> emotionTagList, SortType sortType);

	Slice<Album> findMyAlbumList(Pageable pageable, List<EmotionTag> emotionTagList, SortType sortType, Long userId);
}
