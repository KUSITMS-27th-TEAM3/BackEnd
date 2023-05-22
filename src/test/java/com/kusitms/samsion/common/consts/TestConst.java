package com.kusitms.samsion.common.consts;

import java.util.List;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.kusitms.samsion.domain.album.domain.entity.EmotionTag;
import com.kusitms.samsion.domain.album.domain.entity.SortType;

public class TestConst {

	// jwt
	public static final String TEST_ACCESS_TOKEN = ApplicationConst.JWT_AUTHORIZATION_TYPE+"testAccessToken";

	// 사용자 정보
	public static final Long TEST_USER_ID = 1L;
	public static final Long TEST_ANOTHER_USER_ID = 2L;
	public static final String TEST_EMAIL = "test@test.com";
	public static final String TEST_NICKNAME = "test";
	public static final String TEST_PROFILE_IMAGE_URL = "testImageUrl";
	public static final String TEST_PET_NAME = "testPet";
	public static final String TEST_PET_NICKNAME = "testPetNickname";
	public static final String TEST_PET_IMAGE_URL = "testImageUrl";
	public static final String TEST_DESCRIPTION = "testDescription";
	public static final int TEST_PET_AGE = 1;
	public static final String TEST_PET_TYPE = "testPetType";

	// 사용자 정보 수정
	public static final String TEST_UPDATE_NICKNAME = "testUpdateNickname";
	public static final String TEST_UPDATE_PROFILE_IMAGE_URL = "testUpdateImageUrl";
	public static final String TEST_UPDATE_PET_NAME = "testUpdatePet";
	public static final String TEST_UPDATE_PET_NICKNAME = "testUpdatePetNickname";
	public static final String TEST_UPDATE_PET_IMAGE_URL = "testUpdateImageUrl";
	public static final String TEST_UPDATE_DESCRIPTION = "testUpdateDescription";
	public static final int TEST_UPDATE_PET_AGE = 2;
	public static final String TEST_UPDATE_PET_TYPE = "testUpdatePetType";


	public static final MultipartFile TEST_MULTIPART_FILE = new MockMultipartFile("test","","test.png", "test".getBytes());


	// 공감
	public static final Long TEST_EMPATHY_ID = 1L;
	public static final long TEST_EMPATHY_COUNT = 1L;

	// 앨범
	public static final Long TEST_ALBUM_ID = 1L;
	public static final String TEST_ALBUM_TITLE = "testAlbumTitle";
	public static final String TEST_ALBUM_DESCRIPTION = "testAlbumDescription";
	public static final String TEST_ALBUM_IMAGE_URL = "testAlbumImageUrl";
	public static final String TEST_UPDATE_ALBUM_TITLE = "testUpdateAlbumTitle";
	public static final String TEST_UPDATE_ALBUM_DESCRIPTION = "testUpdateAlbumDescription";
	public static final String TEST_UPDATE_ALBUM_IMAGE_URL = "testUpdateAlbumImageUrl";
	public static final SortType TEST_SORT_TYPE = SortType.DEFAULT;

	// 댓글
	public static final Long TEST_COMMENT_ID = 1L;
	public static final Long TEST_CHILD_ID = 2L;
	public static final String TEST_COMMENT_DESCRIPTION = "testCommentDescription";
	public static final String TEST_CHILD_COMMENT_DESCRIPTION = "testChildCommentDescription";
	public static final String TEST_UPDATE_COMMENT_DESCRIPTION = "testUpdateCommentDescription";
	public static final long TEST_COMMENT_COUNT = 1L;


	// 질문
	public static final String QUESTION_PREFIX_URL = "/question";
	public static final Long TEST_QUESTION_ID = 1L;
	public static final String TEST_QUESTION_TITLE = "testQuestionTitle";
	public static final Long TEST_ANSWER_ID = 1L;
	public static final String TEST_ANSWER_DESCRIPTION = "testAnswerDescription";

	//장례정보
	public static final Long TEST_FUNERALSHOP_ID = 1L;
	public static final String TEST_SHOP_IMG_URL = "imageurl";
	public static final String TEST_RUN_TYPE = "FULL_TIME";

	public static final String TEST_OPENTIME = "6:00";

	public static final String TEST_CLOSINGTIME = "18:00";

	public static final String TEST_AREA = "경기";

	public static final String TEST_SHOPNAME = "위드천사";

	public static final String TEST_ADDRESS = "경기도 강주시 초월음 산월리 592-20";

	public static final String TEST_PHONENUMBER = "1588-4377";

	public static final String TEST_URL = "shopurl";


	// 태그
	public static final List<EmotionTag> EMOTION_TAG_LIST = List.of(EmotionTag.COMFORTABLE);
	public static final Long TEST_TAG_ID = 1L;

	// 앨범 이미지
	public static final Long TEST_ALBUM_IMAGE_ID = 1L;
}