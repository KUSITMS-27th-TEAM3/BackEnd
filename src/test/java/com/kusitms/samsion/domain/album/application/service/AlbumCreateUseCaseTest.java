package com.kusitms.samsion.domain.album.application.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumImageSaveService;
import com.kusitms.samsion.domain.album.domain.service.AlbumSaveService;
import com.kusitms.samsion.domain.album.domain.service.TagSaveService;
import com.kusitms.samsion.domain.user.domain.entity.MyPet;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumCreateUseCase 테스트")
class AlbumCreateUseCaseTest {

	@Mock
	UserUtils userUtils;
	@Mock
	AlbumSaveService albumSaveService;
	@Mock
	AlbumImageSaveService albumImageSaveService;
	@Mock
	TagSaveService tagSaveService;
	@Mock
	S3UploadService s3UploadService;

	@Mock
	ApplicationEventPublisher applicationEventPublisher;

	AlbumCreateUseCase albumCreateUseCase;

	@BeforeEach
	void setUp() {
		albumCreateUseCase = new AlbumCreateUseCase(userUtils, albumSaveService, albumImageSaveService, tagSaveService, s3UploadService, applicationEventPublisher);
	}

	// TODO : 테스트코드 작성
	@Test
	void 앨범을_생성한다(){
		//given
		User mockUser = UserTestUtils.getMockUser();
		MyPet mockUserMypet = mockUser.getMypet();
		Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		AlbumCreateRequest mockAlbumCreateRequest = getMockAlbumCreateRequest();
		List<String> testAlbumImageUrl = List.of(TestConst.TEST_ALBUM_IMAGE_URL);
		given(s3UploadService.uploadImgList(mockAlbumCreateRequest.getAlbumImages())).willReturn(testAlbumImageUrl);
		given(userUtils.getUser()).willReturn(mockUser);
		//when
		AlbumInfoResponse albumInfoResponse = albumCreateUseCase.createAlbum(mockAlbumCreateRequest);
		//then
		then(albumSaveService).should(times(1)).saveAlbum(any(Album.class));
		then(albumImageSaveService).should(times(1)).saveAlbumImageList(anyList(), any(Album.class));
		then(tagSaveService).should(times(1)).saveTagList(anyList(), any(Album.class));

		Assertions.assertThat(albumInfoResponse).isNotNull();
		Assertions.assertThat(albumInfoResponse.getTitle()).isEqualTo(TestConst.TEST_ALBUM_TITLE);
		Assertions.assertThat(albumInfoResponse.getDescription()).isEqualTo(TestConst.TEST_ALBUM_DESCRIPTION);
		Assertions.assertThat(albumInfoResponse.getVisibility()).isEqualTo(mockAlbumCreateRequest.getVisibility());
		Assertions.assertThat(albumInfoResponse.getChangeable()).isEqualTo(Boolean.TRUE);
		Assertions.assertThat(albumInfoResponse.getPetName()).isEqualTo(mockUserMypet.getPetName());
		Assertions.assertThat(albumInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockUser.getProfileImageUrl());
		Assertions.assertThat(albumInfoResponse.getEmotionTagList()).isEqualTo(mockAlbumCreateRequest.getEmotionTags());
	}

	AlbumCreateRequest getMockAlbumCreateRequest() {
		return AlbumCreateRequest.builder()
			.title(TestConst.TEST_ALBUM_TITLE)
			.description(TestConst.TEST_ALBUM_DESCRIPTION)
			.emotionTags(TestConst.EMOTION_TAG_LIST)
			.albumImages(List.of(TestConst.TEST_MULTIPART_FILE))
			.build();
	}

}