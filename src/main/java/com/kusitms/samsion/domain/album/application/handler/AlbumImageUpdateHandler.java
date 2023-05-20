package com.kusitms.samsion.domain.album.application.handler;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumImageUpdateRequest;
import com.kusitms.samsion.domain.album.application.mapper.AlbumImageMapper;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageUpdateService;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AlbumImageUpdateHandler {

	private final AlbumQueryService albumQueryService;
	private final AlbumImageUpdateService albumImageUpdateService;
	private final S3UploadService s3UploadService;

	@TransactionalEventListener
	public void updateAlbumImage(AlbumImageUpdateRequest albumImageUpdateRequest) {
		final Album album = albumQueryService.findAlbumById(albumImageUpdateRequest.getAlbumId());
		List<String> uploadImgList = s3UploadService.uploadImgList(albumImageUpdateRequest.getAddImageList());
		final List<AlbumImage> albumImages =
			AlbumImageMapper.mapToAlbumImageListWithNewAlbumImage(albumImageUpdateRequest.getImageUrlList(), uploadImgList, album);
		albumImageUpdateService.updateAlbumImage(album, albumImages);
	}
}
