package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumUpdateService 테스트")
class AlbumUpdateServiceTest {

    AlbumUpdateService albumUpdateService;

    @BeforeEach
    void setUp() {
        albumUpdateService = new AlbumUpdateService();
    }

    @Test
    void 앨범을_수정한다(){
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        //when
        albumUpdateService.updateAlbum(mockAlbum, TestConst.TEST_UPDATE_ALBUM_TITLE, TestConst.TEST_UPDATE_ALBUM_DESCRIPTION, TestConst.TEST_UPDATE_ALBUM_VISIBILITY);
        //then
        Assertions.assertThat(mockAlbum.getTitle()).isEqualTo(TestConst.TEST_UPDATE_ALBUM_TITLE);
        Assertions.assertThat(mockAlbum.getDescription()).isEqualTo(TestConst.TEST_UPDATE_ALBUM_DESCRIPTION);
        Assertions.assertThat(mockAlbum.getVisibility()).isEqualTo(TestConst.TEST_UPDATE_ALBUM_VISIBILITY);
    }

}