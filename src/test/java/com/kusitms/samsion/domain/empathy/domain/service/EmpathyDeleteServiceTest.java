package com.kusitms.samsion.domain.empathy.domain.service;

import static org.mockito.BDDMockito.*;

import com.kusitms.samsion.common.util.AlbumTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.empathy.domain.repository.EmpathyRepository;
import com.kusitms.samsion.domain.empathy.domain.service.EmpathyDeleteService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmpathyDeleteService 테스트")
class EmpathyDeleteServiceTest {

	@Mock
	private EmpathyRepository empathyRepository;

	EmpathyDeleteService empathyDeleteService;

	@BeforeEach
	void setUp() {
		empathyDeleteService = new EmpathyDeleteService(empathyRepository);
	}

	@Test
	void 공감_삭제_요청을_받는다(){
		//given
		final User mockUser = UserTestUtils.getMockUser();
		final Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		//when
		empathyDeleteService.deleteEmpathy(mockUser.getId(), mockAlbum.getId());
		//then
		then(empathyRepository).should(times(1)).deleteByUserIdAndAlbumId(mockUser.getId(), mockAlbum.getId());
	}


}