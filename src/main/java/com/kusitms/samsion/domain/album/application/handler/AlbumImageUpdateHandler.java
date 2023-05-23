package com.kusitms.samsion.domain.album.application.handler;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumImageUpdateRequest;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageUpdateService;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AlbumImageUpdateHandler {

	private final AlbumQueryService albumQueryService;
	private final AlbumImageUpdateService albumImageUpdateService;
	private final S3UploadService s3UploadService;

	@Async
	@TransactionalEventListener
	public void updateAlbumImage(AlbumImageUpdateRequest albumImageUpdateRequest) {
		final Album album = albumQueryService.findAlbumById(albumImageUpdateRequest.getAlbumId());
		final List<String> uploadImgList = s3UploadService.uploadImgList(albumImageUpdateRequest.getAddImageList());
		albumImageUpdateService.updateAlbumImage(album,albumImageUpdateRequest.getImageUrlList(), uploadImgList);
	}
}
