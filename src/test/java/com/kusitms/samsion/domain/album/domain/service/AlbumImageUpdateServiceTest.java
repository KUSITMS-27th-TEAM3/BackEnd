package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.repository.AlbumImageRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumImageUpdateService 테스트")
class AlbumImageUpdateServiceTest {

    @Mock
    AlbumImageRepository albumImageRepository;

    AlbumImageUpdateService albumImageUpdateService;

    @BeforeEach
    void setUp() {
        albumImageUpdateService = new AlbumImageUpdateService(albumImageRepository);
    }


    @Test
    void 앨범_이미지를_업데이트한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        List<AlbumImage> albumImageList = Stream.of(TestConst.TEST_UPDATE_ALBUM_IMAGE_URL)
                .map(url -> new AlbumImage(url, mockAlbum))
                .collect(Collectors.toList());
        //when
        albumImageUpdateService.updateAlbumImage(mockAlbum, albumImageList);
        //then
        then(albumImageRepository).should().saveAll(albumImageList);
        Assertions.assertThat(mockAlbum.getAlbumImages()).isEqualTo(albumImageList);
    }
}