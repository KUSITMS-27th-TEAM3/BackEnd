package com.kusitms.samsion.common.util;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumTestUtils {

	public static Album getMockAlbum(User mockUser) {
		Album mockAlbum = Album.builder()
			.writer(mockUser)
			.title(TestConst.TEST_ALBUM_TITLE)
			.visibility(TestConst.TEST_ALBUM_VISIBILITY)
			.description(TestConst.TEST_DESCRIPTION)
			.build();
		ReflectionTestUtils.setField(mockAlbum, "id", TestConst.TEST_ALBUM_ID);

		mockAlbum.changeAllImage(getMockAlbumImageList(mockAlbum));
		mockAlbum.changeAllTag(getMockTagList(mockAlbum));
		return mockAlbum;
	}

	private static List<AlbumImage> getMockAlbumImageList(Album mockAlbum) {
		AlbumImage albumImage = new AlbumImage(TestConst.TEST_ALBUM_IMAGE_URL, mockAlbum);
		ReflectionTestUtils.setField(albumImage, "id", TestConst.TEST_ALBUM_IMAGE_ID);
		return new ArrayList<>(List.of(albumImage));
	}

	private static List<Tag> getMockTagList(Album mockAlbum) {
		List<Tag> tagList = TestConst.EMOTION_TAG_LIST.stream()
			.map(tag -> new Tag(tag, mockAlbum))
			.collect(Collectors.toList());
		tagList.forEach(tag -> ReflectionTestUtils.setField(tag, "id", TestConst.TEST_TAG_ID));
		return new ArrayList<>(tagList);
	}
}
