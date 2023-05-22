package com.kusitms.samsion.domain.album.application.handler;

import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumImageUpdateRequest;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageUpdateService;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumImageUpdateHandler 테스트")
class AlbumImageUpdateHandlerTest {

	@Mock
	AlbumQueryService albumQueryService;
	@Mock
	AlbumImageUpdateService albumImageUpdateService;
	@Mock
	S3UploadService s3UploadService;

	AlbumImageUpdateHandler albumImageUpdateHandler;

	@BeforeEach
	void setUp() {
		albumImageUpdateHandler = new AlbumImageUpdateHandler(albumQueryService, albumImageUpdateService,
			s3UploadService);
	}

	@Test
	void 앨범_이미지를_업데이트한다() {
		//given
		User mockUser = UserTestUtils.getMockUser();
		Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		mockAlbum.clearAllImage();
		AlbumImageUpdateRequest mockAlbumImageUpdateRequest = getMockAlbumImageUpdateRequest(mockAlbum.getId());
		given(albumQueryService.findAlbumById(mockAlbum.getId())).willReturn(mockAlbum);
		given(s3UploadService.uploadImgList(mockAlbumImageUpdateRequest.getAddImageList()))
			.willReturn(List.of(TestConst.TEST_UPDATE_ALBUM_IMAGE_URL));
		Stream.of(TestConst.TEST_UPDATE_ALBUM_IMAGE_URL, TestConst.TEST_ALBUM_IMAGE_URL)
			.map(url -> new AlbumImage(url, mockAlbum)).collect(Collectors.toList());
		//when
		albumImageUpdateHandler.updateAlbumImage(mockAlbumImageUpdateRequest);
		//then
		then(albumImageUpdateService).should(times(1))
			.updateAlbumImage(eq(mockAlbum), anyList());
	}

	AlbumImageUpdateRequest getMockAlbumImageUpdateRequest(Long albumId) {
		return new AlbumImageUpdateRequest(albumId, List.of(TestConst.TEST_ALBUM_IMAGE_URL),
			List.of(TestConst.TEST_MULTIPART_FILE));
	}

}