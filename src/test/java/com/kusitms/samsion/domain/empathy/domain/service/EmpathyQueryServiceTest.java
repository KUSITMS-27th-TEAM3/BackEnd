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
import com.kusitms.samsion.domain.empathy.domain.service.EmpathyQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmpathyQueryService 테스트")
class EmpathyQueryServiceTest {

	@Mock
	private EmpathyRepository empathyRepository;

	EmpathyQueryService empathyQueryService;

	@BeforeEach
	void setUp() {
		empathyQueryService = new EmpathyQueryService(empathyRepository);
	}

	@Test
	void 공감_여부_조회_요청을_받는다(){
		//given
		final User mockUser = UserTestUtils.getMockUser();
		final Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		//when
		empathyQueryService.isEmpathyByUserIdAndAlbumId(mockUser.getId(), mockAlbum.getId());
		//then
		then(empathyRepository).should(times(1)).existsByUserIdAndAlbumId(mockUser.getId(), mockAlbum.getId());
	}
}