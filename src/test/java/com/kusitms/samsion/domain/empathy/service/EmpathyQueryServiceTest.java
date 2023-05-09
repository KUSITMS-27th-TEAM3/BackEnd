package com.kusitms.samsion.domain.empathy.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.Visibility;
import com.kusitms.samsion.domain.empathy.repository.EmpathyRepository;
import com.kusitms.samsion.domain.user.entity.User;

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
		final Album mockAlbum = getMockAlbum(mockUser);
		//when
		empathyQueryService.isEmpathyByUserIdAndAlbumId(mockUser.getId(), mockAlbum.getId());
		//then
		then(empathyRepository).should(times(1)).existsByUserIdAndAlbumId(mockUser.getId(), mockAlbum.getId());
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