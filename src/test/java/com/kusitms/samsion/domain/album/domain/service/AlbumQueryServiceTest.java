package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.exception.AlbumNotFoundException;
import com.kusitms.samsion.domain.album.domain.repository.AlbumRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumQueryService 테스트")
class AlbumQueryServiceTest {

    @Mock
    AlbumRepository albumRepository;

    AlbumQueryService albumQueryService;

    @BeforeEach
    void setUp() {
        albumQueryService = new AlbumQueryService(albumRepository);
    }

    @Nested
    class 앨범_조회_테스트 {
        @Test
        void 앨범을_조회한다() {
            //given
            User mockUser = UserTestUtils.getMockUser();
            Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
            given(albumRepository.findById(mockAlbum.getId())).willReturn(Optional.of(mockAlbum));
            //when
            Album album = albumQueryService.findAlbumById(mockAlbum.getId());
            //then
            Assertions.assertThat(album).usingRecursiveComparison().isEqualTo(mockAlbum);
        }

        @Test
        void 앨범이_존재하지_않으면_예외가_발생한다(){
            //given
            given(albumRepository.findById(TestConst.TEST_ALBUM_ID)).willReturn(Optional.empty());
            //when
            //then
            Assertions.assertThatThrownBy(()-> albumQueryService.findAlbumById(TestConst.TEST_ALBUM_ID))
                    .isInstanceOf(AlbumNotFoundException.class);
        }
    }

    @Test
    void 앨범_전체_조회(){
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        given(albumRepository.findAlbumList(mockPageable, TestConst.EMOTION_TAG_LIST, TestConst.TEST_SORT_TYPE)).willReturn(SliceTestUtils.getMockSlice(List.of(mockAlbum)));
        //when
        Slice<Album> albumList = albumQueryService.findAlbumList(mockPageable, TestConst.EMOTION_TAG_LIST, TestConst.TEST_SORT_TYPE);
        //then
        Assertions.assertThat(albumList.getContent()).usingRecursiveComparison().isEqualTo(List.of(mockAlbum));
    }

    @Test
    void 사용자_앨범_전체_조회(){
        //given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        given(albumRepository.findMyAlbumList(mockPageable, TestConst.EMOTION_TAG_LIST, TestConst.TEST_SORT_TYPE, mockUser.getId())).willReturn(SliceTestUtils.getMockSlice(List.of(mockAlbum)));
        //when
        Slice<Album> albumList = albumQueryService.findMyAlbumList(mockPageable, TestConst.EMOTION_TAG_LIST, TestConst.TEST_SORT_TYPE, mockUser.getId());
        //then
        Assertions.assertThat(albumList.getContent()).usingRecursiveComparison().isEqualTo(List.of(mockAlbum));
    }


}