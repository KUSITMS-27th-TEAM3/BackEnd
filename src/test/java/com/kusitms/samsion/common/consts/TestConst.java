package com.kusitms.samsion.common.consts;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestConst {

	public static final Long TEST_ID = 1L;
	public static final String TEST_EMAIL = "test@test.com";
	public static final String TEST_NICKNAME = "test";
	public static final String TEST_PET_NAME = "testPet";
	public static final String TEST_PET_IMAGE_URL = "testImageUrl";
	public static final String TEST_DESCRIPTION = "testDescription";
	public static final String TEST_UPDATE_PET_NAME = "testUpdatePet";
	public static final String TEST_UPDATE_PET_IMAGE_URL = "testUpdateImageUrl";
	public static final String TEST_UPDATE_DESCRIPTION = "testUpdateDescription";

	public static final MultipartFile TEST_MULTIPART_FILE = new MockMultipartFile("test","","test.png", "test".getBytes());
}
