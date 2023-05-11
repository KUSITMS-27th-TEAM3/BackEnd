package com.kusitms.samsion.common.consts;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestConst {

	// jwt
	public static final String TEST_ACCESS_TOKEN = ApplicationConst.JWT_AUTHORIZATION_TYPE+"testAccessToken";

	// 사용자 정보
	public static final Long TEST_USER_ID = 1L;
	public static final String TEST_EMAIL = "test@test.com";
	public static final String TEST_NICKNAME = "test";
	public static final String TEST_PET_NAME = "testPet";
	public static final String TEST_PET_IMAGE_URL = "testImageUrl";
	public static final String TEST_DESCRIPTION = "testDescription";
	public static final String TEST_UPDATE_PET_NAME = "testUpdatePet";
	public static final String TEST_UPDATE_PET_IMAGE_URL = "testUpdateImageUrl";
	public static final String TEST_UPDATE_DESCRIPTION = "testUpdateDescription";

	public static final MultipartFile TEST_MULTIPART_FILE = new MockMultipartFile("test","","test.png", "test".getBytes());


	// 공감
	public static final Long TEST_EMPATHY_ID = 1L;


	// 질문
	public static final String QUESTION_PREFIX_URL = "/question";
	public static final Long TEST_QUESTION_ID = 1L;
	public static final String TEST_QUESTION_TITLE = "testQuestionTitle";
	public static final Long TEST_ANSWER_ID = 1L;
	public static final String TEST_ANSWER_DESCRIPTION = "testAnswerDescription";


}
