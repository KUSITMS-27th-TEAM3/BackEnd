package com.kusitms.samsion.application.empathy.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.Visibility;
import com.kusitms.samsion.domain.album.service.AlbumQueryService;
import com.kusitms.samsion.domain.empathy.service.EmpathyDeleteService;
import com.kusitms.samsion.domain.empathy.service.EmpathyQueryService;
import com.kusitms.samsion.domain.empathy.service.EmpathySaveService;
import com.kusitms.samsion.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmpathyToggleService 테스트")
class EmpathyToggleServiceTest {

	@Mock
	private UserUtils userUtils;
	@Mock
	private EmpathyQueryService empathyQueryService;
	@Mock
	private EmpathySaveService empathySaveService;
	@Mock
	private EmpathyDeleteService empathyDeleteService;
	@Mock
	private AlbumQueryService albumQueryService;


	private EmpathyToggleService empathyToggleService;

	@BeforeEach
	void setUp() {
		empathyToggleService = new EmpathyToggleService(userUtils, empathyQueryService, empathySaveService, empathyDeleteService, albumQueryService);
	}

	@Test
	void 처음_공감_버튼을_눌렀을때_empathy을_생성한다(){
		//given
		final User mockUser = UserTestUtils.getMockUser();
		final Album mockAlbum = getMockAlbum(mockUser);
		given(userUtils.getUser()).willReturn(mockUser);
		given(empathyQueryService.isEmpathyByUserIdAndAlbumId(anyLong(), anyLong())).willReturn(false);
		given(albumQueryService.getAlbumById(anyLong())).willReturn(mockAlbum);
		//when
		empathyToggleService.toggleEmpathy(mockAlbum.getId());
		//then
		then(empathySaveService).should(times(1)).saveEmpathy(mockUser, mockAlbum);
		then(empathyDeleteService).should(times(0)).deleteEmpathy(anyLong(), anyLong());
	}

	@Test
	void 이미_공감이_존재하는경우_삭제한다(){
		//given
		final User mockUser = UserTestUtils.getMockUser();
		final Album mockAlbum = getMockAlbum(mockUser);
		given(userUtils.getUser()).willReturn(mockUser);
		given(empathyQueryService.isEmpathyByUserIdAndAlbumId(anyLong(), anyLong())).willReturn(true);
		//when
		empathyToggleService.toggleEmpathy(mockAlbum.getId());
		//then
		then(empathySaveService).should(times(0)).saveEmpathy(any(User.class), any(Album.class));
		then(empathyDeleteService).should(times(1)).deleteEmpathy(mockUser.getId(), mockAlbum.getId());
	}

	private Album getMockAlbum(User mockUser) {
		Album mockAlbum = Album.builder()
			.writer(mockUser)
			.visibility(Visibility.PUBLIC)
			.description("album description")
			.build();
		ReflectionTestUtils.setField(mockAlbum, "id", 1L);
		return mockAlbum;
	}

}