package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.repository.AlbumImageRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumImageSaveService 테스트")
class AlbumImageSaveServiceTest {

    @Mock
    AlbumImageRepository albumImageRepository;

    AlbumImageSaveService albumImageSaveService;

    @BeforeEach
    void setUp() {
        albumImageSaveService = new AlbumImageSaveService(albumImageRepository);
    }

    @Test
    void 앨범_이미지_리시트를_저장한다() {
        //given
        List<String> albumImageUrl = new ArrayList<>(List.of(TestConst.TEST_ALBUM_IMAGE_URL));
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        //when
        albumImageSaveService.saveAlbumImageList(albumImageUrl, mockAlbum);
        //then
        albumImageUrl.forEach(url -> {
                    then(albumImageRepository).should().save(refEq(new AlbumImage(url, mockAlbum)));
                }
        );
    }

}