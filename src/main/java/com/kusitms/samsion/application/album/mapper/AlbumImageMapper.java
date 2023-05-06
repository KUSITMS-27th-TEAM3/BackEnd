package com.kusitms.samsion.application.album.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.AlbumImage;

@Mapper
public class AlbumImageMapper {


	public List<AlbumImage> mapToAlbumImageListWithAlbum(List<String> imageUrls, Album album) {
		return imageUrls.stream().map(imageUrl->new AlbumImage(imageUrl,album)).collect(Collectors.toList());
	}
}
