package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.repository.AlbumImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
@Transactional
public class AlbumImageUpdateService {

	private final AlbumImageRepository albumImageRepository;

	public void updateAlbumImage(Album album,List<String> oldImageList, List<String> newImageList){
		List<AlbumImage> albumImageList = oldImageList.stream().map(imageUrl->new AlbumImage(imageUrl,album)).collect(Collectors.toList());
		albumImageList.addAll(newImageList.stream().map(imageUrl->new AlbumImage(imageUrl,album)).collect(Collectors.toList()));
		albumImageRepository.saveAll(albumImageList);
	}
}
