package com.kusitms.samsion.domain.album.application.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumDeleteService;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.AlbumValidAccessService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumDeleteUseCase 테스트")
class AlbumDeleteUseCaseTest {

	@Mock
	UserUtils userUtils;
	@Mock
	AlbumQueryService albumQueryService;
	@Mock
	AlbumValidAccessService albumValidAccessService;
	@Mock
	AlbumDeleteService albumDeleteService;

	AlbumDeleteUseCase albumDeleteUseCase;

	@BeforeEach
	void setUp() {
		albumDeleteUseCase = new AlbumDeleteUseCase(userUtils, albumQueryService, albumValidAccessService, albumDeleteService);
	}

	@Test
	void 앨범을_삭제한다() {
		//given
		User mockUser = UserTestUtils.getMockUser();
		Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		given(userUtils.getUser()).willReturn(mockUser);
		given(albumQueryService.findAlbumById(mockAlbum.getId())).willReturn(mockAlbum);
		//when
		albumDeleteUseCase.deleteAlbum(mockAlbum.getId());
		//then
		then(albumValidAccessService).should(times(1)).validateAccess(mockAlbum, mockUser.getId());
		then(albumDeleteService).should(times(1)).deleteAlbum(mockAlbum.getId());
	}

}