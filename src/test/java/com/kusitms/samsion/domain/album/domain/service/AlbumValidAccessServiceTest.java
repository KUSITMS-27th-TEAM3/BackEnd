package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.exception.AlbumAccessDeniedException;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumValidAccessService 테스트")
class AlbumValidAccessServiceTest {

    AlbumValidAccessService albumValidAccessService;

    @BeforeEach
    void setUp() {
        albumValidAccessService = new AlbumValidAccessService();
    }

    @Nested
    class 앨범_접근_테스트{
        @Test
        void PUBLIC_앨범에_작성자가_접근하면_예외가_발생하지_않는다() {
            // given
            User mockUser = UserTestUtils.getMockUser();
            Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
            // when
            // then
            Assertions.assertThatCode(() -> albumValidAccessService.validateAccess(mockAlbum, mockUser.getId()))
                    .doesNotThrowAnyException();
        }

        @Test
        void PRIVATE_앨범에_작성자가_접근하면_예외가_발생하지_않는다() {
            // given
            User mockUser = UserTestUtils.getMockUser();
            Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
            mockAlbum.updateVisibility(TestConst.TEST_UPDATE_ALBUM_VISIBILITY);
            // when
            // then
            Assertions.assertThatCode(() -> albumValidAccessService.validateAccess(mockAlbum, mockUser.getId()))
                    .doesNotThrowAnyException();
        }

        @Test
        void PUBLIC_앨범에_작성자가_아닌_사용자가_접근하면_예외가_발생하지_않는다(){
            //given
            User mockUser = UserTestUtils.getMockUser();
            User anotherMockUser = UserTestUtils.getAnotherMockUser();
            Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
            //when
            //then
            Assertions.assertThatCode(() -> albumValidAccessService.validateAccess(mockAlbum, anotherMockUser.getId()))
                    .doesNotThrowAnyException();
        }

        @Test
        void PRIVATE_앨범에_작성자가_아닌_사용자가_접근하면_예외가_발생하지_않는다(){
            //given
            User mockUser = UserTestUtils.getMockUser();
            User anotherMockUser = UserTestUtils.getAnotherMockUser();
            Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
            mockAlbum.updateVisibility(TestConst.TEST_UPDATE_ALBUM_VISIBILITY);
            //when
            //then
            Assertions.assertThatThrownBy(() -> albumValidAccessService.validateAccess(mockAlbum, anotherMockUser.getId()))
                    .isInstanceOf(AlbumAccessDeniedException.class);
        }


    }


}