package com.kusitms.samsion.domain.album.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.user.domain.entity.User;

@Mapper
public class AlbumMapper {

	public static AlbumSimpleResponse mapToAlbumSimpleResponse(Album album, long commentCnt, long empathyCnt) {
		return AlbumSimpleResponse.builder()
			.albumId(album.getId())
			.title(album.getTitle())
			.imageUrl(album.getAlbumImages().get(0).getImageUrl())
			.commentCount(commentCnt)
			.empathyCount(empathyCnt)
			.build();
	}

	public static AlbumInfoResponse mapToAlbumInfoResponse(Album album, long commentCnt, long empathyCnt, List<EmotionTag> emotionTagList) {
		final User writer = album.getWriter();
		return AlbumInfoResponse.builder()
			.imageUrlList(album.getAlbumImages().stream().map(AlbumImage::getImageUrl).collect(Collectors.toList()))
			.title(album.getTitle())
			.description(album.getDescription())
			.visibility(album.getVisibility())
			.writer(writer.getNickname())
			.petName(writer.getMypet().getPetName())
			.writerProfileImageUrl(writer.getMypet().getPetImageUrl())
			.commentCount(commentCnt)
			.empathyCount(empathyCnt)
			.emotionTagList(emotionTagList)
			.build();
	}


	public static Album mapToAlbumWithUser(AlbumCreateRequest request, User user) {
		return Album.builder()
			.title(request.getTitle())
			.description(request.getDescription())
			.visibility(request.getVisibility())
			.writer(user)
			.build();
	}

}
