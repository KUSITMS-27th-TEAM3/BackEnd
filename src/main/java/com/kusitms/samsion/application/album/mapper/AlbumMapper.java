package com.kusitms.samsion.application.album.mapper;

import org.springframework.data.domain.Slice;

import com.kusitms.samsion.application.album.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.application.album.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.application.album.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.user.entity.User;

@Mapper
public class AlbumMapper {

	public Slice<AlbumSimpleResponse> mapToAlbumSimpleResponse(Slice<Album> albumSlice) {
		return albumSlice.map(album -> new AlbumSimpleResponse(album.getId(), null));
	}

	public AlbumInfoResponse mapToAlbumInfoResponse(Album album) {
		final User writer = album.getWriter();
		return AlbumInfoResponse.builder()
			.imageUrl(album.getAlbumImages().get(0).getImageUrl())
			.description(album.getDescription())
			.writer(writer.getNickname())
			.writerProfileImageUrl(writer.getMypet().getPetImageUrl())
			.build();
	}


	public Album mapToAlbumWithUser(AlbumCreateRequest request, User user) {
		return Album.builder()
			.description(request.getDescription())
			.visibility(request.getVisibility())
			.writer(user)
			.build();
	}

}
