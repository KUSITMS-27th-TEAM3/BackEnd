package com.kusitms.samsion.common.util;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.entity.Visibility;
import com.kusitms.samsion.domain.user.entity.User;
import org.springframework.test.util.ReflectionTestUtils;

public class AlbumTestUtils {

    public static Album getMockAlbum(User mockUser) {
        Album mockAlbum = Album.builder()
                .writer(mockUser)
                .visibility(Visibility.PUBLIC)
                .description("album description")
                .build();
        ReflectionTestUtils.setField(mockAlbum, "id", TestConst.TEST_ID);
        return mockAlbum;
    }
}
