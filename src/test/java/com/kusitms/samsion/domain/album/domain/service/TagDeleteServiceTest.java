package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("TagDeleteService 테스트")
class TagDeleteServiceTest {

    @Mock
    TagRepository tagRepository;

    TagDeleteService tagDeleteService;

    @BeforeEach
    void setUp() {
        tagDeleteService = new TagDeleteService(tagRepository);
    }

    @Test
    void 앨범의_태그를_모두_삭제한다() {
        // given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        // when
        tagDeleteService.deleteTagList(mockAlbum);
        // then
        then(tagRepository).should().deleteAllByAlbumId(mockAlbum.getId());
        Assertions.assertThat(mockAlbum.getTags()).isEmpty();
    }

}