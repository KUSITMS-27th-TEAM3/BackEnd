package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("TagUpdateService 테스트")
class TagUpdateServiceTest {

    @Mock
    TagRepository tagRepository;

    TagUpdateService tagUpdateService;

    @BeforeEach
    void setUp() {
        tagUpdateService = new TagUpdateService(tagRepository);
    }

    @Test
    void 앨범의_태그리스트를_모두_수정한다() {
        // given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        List<EmotionTag> emotionTagList = TestConst.EMOTION_TAG_LIST;
        // when
        tagUpdateService.updateTag(mockAlbum, emotionTagList);
        // then
        then(tagRepository).should().saveAll(mockAlbum.getTags());
    }

}