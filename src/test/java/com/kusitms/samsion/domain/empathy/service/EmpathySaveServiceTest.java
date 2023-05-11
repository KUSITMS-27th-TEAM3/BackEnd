package com.kusitms.samsion.domain.empathy.service;

import static org.mockito.BDDMockito.*;

import com.kusitms.samsion.common.util.AlbumTestUtils;
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
import com.kusitms.samsion.domain.empathy.entity.Empathy;
import com.kusitms.samsion.domain.empathy.repository.EmpathyRepository;
import com.kusitms.samsion.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmpathySaveService 테스트")
class EmpathySaveServiceTest {

	@Mock
	private EmpathyRepository empathyRepository;

	EmpathySaveService empathySaveService;

	@BeforeEach
	void setUp() {
		empathySaveService = new EmpathySaveService(empathyRepository);
	}

	@Test
	void 공감_저장_요청을_받는다(){
		//given
		final User mockUser = UserTestUtils.getMockUser();
		final Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		//when
		empathySaveService.saveEmpathy(mockUser, mockAlbum);
		//then
		then(empathyRepository).should(times(1)).save(any(Empathy.class));
	}

}