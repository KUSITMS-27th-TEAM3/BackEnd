package com.kusitms.samsion.common.util;

import org.springframework.test.util.ReflectionTestUtils;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.Visibility;
import com.kusitms.samsion.domain.user.entity.User;

public class AlbumTestUtils {

    public static Album getMockAlbum(User mockUser) {
        Album mockAlbum = Album.builder()
                .writer(mockUser)
                .visibility(Visibility.PUBLIC)
                .description("album description")
                .build();
        ReflectionTestUtils.setField(mockAlbum, "id", TestConst.TEST_ALBUM_ID);
        return mockAlbum;
    }
}
