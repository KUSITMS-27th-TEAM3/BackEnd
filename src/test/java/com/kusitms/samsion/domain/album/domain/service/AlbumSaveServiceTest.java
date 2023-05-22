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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumSaveService 테스트")
class AlbumSaveServiceTest {

    @Mock
    AlbumRepository albumRepository;

    AlbumSaveService albumSaveService;

    @BeforeEach
    void setUp() {
        albumSaveService = new AlbumSaveService(albumRepository);
    }

    @Test
    void 앨범을_저장한다(){
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        //when
        albumSaveService.saveAlbum(mockAlbum);
        //then
        then(albumRepository).should().save(mockAlbum);
    }


}