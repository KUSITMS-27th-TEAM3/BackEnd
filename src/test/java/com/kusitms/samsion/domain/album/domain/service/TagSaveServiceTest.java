package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.album.domain.repository.TagRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("TagSaveService 테스트")
class TagSaveServiceTest {

    @Mock
    TagRepository tagRepository;

    TagSaveService tagSaveService;

    @BeforeEach
    void setUp() {
        tagSaveService = new TagSaveService(tagRepository);
    }

    @Test
    void 태그를_앨범에_모두_저장한다(){
        // given
        User mockUser = UserTestUtils.getMockUser();
        Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
        mockAlbum.clearAllTag();
        List<EmotionTag> emotionTagList = TestConst.EMOTION_TAG_LIST;
        // when
        tagSaveService.saveTagList(emotionTagList, mockAlbum);
        // then
        emotionTagList.forEach(emotionTag -> {
            then(tagRepository).should().save(any(Tag.class));
        });
        Assertions.assertThat(mockAlbum.getTags()).hasSize(emotionTagList.size());
    }

}