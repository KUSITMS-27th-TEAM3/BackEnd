package com.kusitms.samsion.application.user.service;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.application.user.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.application.user.dto.response.MyPetResponse;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.entity.User;
import com.kusitms.samsion.infrastructure.s3.S3UploadService;

@ExtendWith(MockitoExtension.class)
@DisplayName("MyPetUpdateService 테스트")
class MyPetUpdateServiceTest {

	@Mock
	private UserUtils userUtils;
	@Mock
	private S3UploadService s3UploadService;

	MyPetUpdateService myPetUpdateService;

	@BeforeEach
	void setUp() {
		myPetUpdateService = new MyPetUpdateService(userUtils, s3UploadService);
	}

	@Test
	void 현재_사용자의_mypet_정보를_업데이트한다(){
		//given
		final MyPetUpdateRequest mockRequest = getMockMyPetUpdateRequest();
		final User mockUser = UserTestUtils.getMockUser();
		given(s3UploadService.uploadImg(mockRequest.getFile())).willReturn(TestConst.TEST_UPDATE_PET_IMAGE_URL);
		given(userUtils.getUser()).willReturn(mockUser);
		//when
		MyPetResponse myPetResponse = myPetUpdateService.updateMyPetInfo(mockRequest);
		//then
		Assertions.assertThat(mockUser.getMypet().getPetName()).isEqualTo(mockRequest.getPetName());
		Assertions.assertThat(mockUser.getMypet().getDescription()).isEqualTo(mockRequest.getDescription());
		Assertions.assertThat(mockUser.getMypet().getPetImageUrl()).isEqualTo(TestConst.TEST_UPDATE_PET_IMAGE_URL);
		Assertions.assertThat(myPetResponse).isNotNull();
		Assertions.assertThat(myPetResponse.getPetName()).isEqualTo(mockRequest.getPetName());
		Assertions.assertThat(myPetResponse.getDescription()).isEqualTo(mockRequest.getDescription());
		Assertions.assertThat(myPetResponse.getPetImageUrl()).isEqualTo(TestConst.TEST_UPDATE_PET_IMAGE_URL);
	}

	MyPetUpdateRequest getMockMyPetUpdateRequest() {
		return MyPetUpdateRequest.builder()
			.petName(TestConst.TEST_UPDATE_PET_NAME)
			.description(TestConst.TEST_UPDATE_DESCRIPTION)
			.file(TestConst.TEST_MULTIPART_FILE)
			.build();
	}
}