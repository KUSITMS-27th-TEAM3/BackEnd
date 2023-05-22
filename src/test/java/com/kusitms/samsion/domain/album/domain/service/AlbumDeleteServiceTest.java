package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.repository.AlbumRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumDeleteService 테스트")
class AlbumDeleteServiceTest {

    @Mock
    AlbumRepository albumRepository;

    AlbumDeleteService albumDeleteService;

    @BeforeEach
    void setUp() {
        albumDeleteService = new AlbumDeleteService(albumRepository);
    }

    @Test
    void 앨범을_삭제한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        //when
        albumDeleteService.deleteAlbum(mockAlbum.getId());
        //then
        then(albumRepository).should(times(1)).deleteById(mockAlbum.getId());
    }

}