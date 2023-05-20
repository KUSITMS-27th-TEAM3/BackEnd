package com.kusitms.samsion.domain.album.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;

@Mapper
public class AlbumImageMapper {


	public static List<AlbumImage> mapToAlbumImageListWithAlbum(List<String> imageUrls, Album album) {
		return imageUrls.stream().map(imageUrl->new AlbumImage(imageUrl,album)).collect(Collectors.toList());
	}

	public static List<AlbumImage> mapToAlbumImageListWithNewAlbumImage(List<String> oldImageList, List<String> newImageList, Album album){
		List<AlbumImage> albumImageList = oldImageList.stream().map(imageUrl->new AlbumImage(imageUrl,album)).collect(Collectors.toList());
		albumImageList.addAll(newImageList.stream().map(imageUrl->new AlbumImage(imageUrl,album)).collect(Collectors.toList()));
		return albumImageList;
	}

}
