package com.kusitms.samsion.domain.user.application.service;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kusitms.samsion.domain.user.application.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;

@ExtendWith(MockitoExtension.class)
@DisplayName("MyPetUpdateService 테스트")
class MyPetUpdateUseCaseTest {

	@Mock
	private UserUtils userUtils;
	@Mock
	private S3UploadService s3UploadService;

	MyPetUpdateUseCase myPetUpdateUseCase;

	@BeforeEach
	void setUp() {
		myPetUpdateUseCase = new MyPetUpdateUseCase(userUtils, s3UploadService);
	}

	@Test
	void 현재_사용자의_mypet_정보를_업데이트한다(){
		//given
		final MyPetUpdateRequest mockRequest = getMockMyPetUpdateRequest();
		final User mockUser = UserTestUtils.getMockUser();
		given(s3UploadService.uploadImg(mockRequest.getProfileImage())).willReturn(TestConst.TEST_UPDATE_PROFILE_IMAGE_URL);
		given(s3UploadService.uploadImg(mockRequest.getPetImage())).willReturn(TestConst.TEST_UPDATE_PET_IMAGE_URL);
		given(userUtils.getUser()).willReturn(mockUser);
		//when
		MyPetResponse myPetResponse = myPetUpdateUseCase.updateMyPetInfo(mockRequest);
		//then
		Assertions.assertThat(mockUser.getMypet().getPetName()).isEqualTo(mockRequest.getPetName());
		Assertions.assertThat(mockUser.getMypet().getDescription()).isEqualTo(mockRequest.getDescription());
		Assertions.assertThat(mockUser.getMypet().getPetAge()).isEqualTo(mockRequest.getPetAge());
		Assertions.assertThat(mockUser.getMypet().getPetType()).isEqualTo(mockRequest.getPetType());
		Assertions.assertThat(mockUser.getNickname()).isEqualTo(mockRequest.getNickname());
		Assertions.assertThat(mockUser.getMypet().getPetImageUrl()).isEqualTo(TestConst.TEST_UPDATE_PET_IMAGE_URL);
		Assertions.assertThat(mockUser.getProfileImageUrl()).isEqualTo(TestConst.TEST_UPDATE_PET_IMAGE_URL);
		Assertions.assertThat(myPetResponse).isNotNull();
		Assertions.assertThat(myPetResponse.getPetName()).isEqualTo(mockRequest.getPetName());
		Assertions.assertThat(myPetResponse.getDescription()).isEqualTo(mockRequest.getDescription());
		Assertions.assertThat(myPetResponse.getPetImageUrl()).isEqualTo(TestConst.TEST_UPDATE_PET_IMAGE_URL);
		Assertions.assertThat(myPetResponse.getPetAge()).isEqualTo(mockRequest.getPetAge());
		Assertions.assertThat(myPetResponse.getPetType()).isEqualTo(mockRequest.getPetType());
	}

	MyPetUpdateRequest getMockMyPetUpdateRequest() {
		return MyPetUpdateRequest.builder()
			.petName(TestConst.TEST_UPDATE_PET_NAME)
			.description(TestConst.TEST_UPDATE_DESCRIPTION)
			.petImage(TestConst.TEST_MULTIPART_FILE)
			.nickname(TestConst.TEST_UPDATE_NICKNAME)
			.profileImage(TestConst.TEST_MULTIPART_FILE)
			.petAge(TestConst.TEST_UPDATE_PET_AGE)
			.petType(TestConst.TEST_UPDATE_PET_TYPE)
			.build();
	}
}