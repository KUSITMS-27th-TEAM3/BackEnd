package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.repository.AlbumImageRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumImageDeleteService 테스트")
class AlbumImageDeleteServiceTest {

    @Mock
    AlbumImageRepository albumImageRepository;

    AlbumImageDeleteService albumImageDeleteService;

    @BeforeEach
    void setUp() {
        albumImageDeleteService = new AlbumImageDeleteService(albumImageRepository);
    }

    @Test
    void 앨범_이미지를_삭제한다(){
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        //when
        albumImageDeleteService.deleteAlbumImageList(mockAlbum);
        //then
        then(albumImageRepository).should(times(1)).deleteAllByAlbumId(mockAlbum.getId());
        Assertions.assertThat(mockAlbum.getAlbumImages()).isEmpty();
    }

}