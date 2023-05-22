package com.kusitms.samsion.domain.album.application.handler;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.application.dto.request.TagUpdateRequest;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.TagUpdateService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("TagUpdateHandler 테스트")
class TagUpdateHandlerTest {

	@Mock
	AlbumQueryService albumQueryService;
	@Mock
	TagUpdateService tagUpdateService;

	TagUpdateHandler tagUpdateHandler;

	@BeforeEach
	void setUp() {
		tagUpdateHandler = new TagUpdateHandler(albumQueryService, tagUpdateService);
	}

	@Test
	void 태그를_수정한다() {
		//given
		User mockUser = UserTestUtils.getMockUser();
		Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		mockAlbum.clearAllTag();
		given(albumQueryService.findAlbumById(mockAlbum.getId())).willReturn(mockAlbum);
		//when
		tagUpdateHandler.updateTag(getMockTagUpdateRequest(mockAlbum.getId()));
		//then
		then(tagUpdateService).should(times(1))
			.updateTag(eq(mockAlbum), eq(mockAlbum.getTags()));
	}

	TagUpdateRequest getMockTagUpdateRequest(Long mockAlbumId) {
		return new TagUpdateRequest(mockAlbumId, TestConst.EMOTION_TAG_LIST);
	}
}