package com.kusitms.samsion.domain.album.application.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.AlbumTestUtils;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumSearchRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.entity.Tag;
import com.kusitms.samsion.domain.album.domain.service.AlbumQueryService;
import com.kusitms.samsion.domain.album.domain.service.AlbumValidAccessService;
import com.kusitms.samsion.domain.comment.domain.service.CommentQueryService;
import com.kusitms.samsion.domain.empathy.domain.service.EmpathyQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlbumReadUseCase 테스트")
class AlbumReadUseCaseTest {

	@Mock
	UserUtils userUtils;
	@Mock
	AlbumQueryService albumQueryService;
	@Mock
	AlbumValidAccessService albumValidAccessService;
	@Mock
	CommentQueryService commentQueryService;
	@Mock
	EmpathyQueryService empathyQueryService;

	AlbumReadUseCase albumReadUseCase;

	@BeforeEach
	void setUp() {
		albumReadUseCase = new AlbumReadUseCase(userUtils, albumQueryService, albumValidAccessService,
			commentQueryService, empathyQueryService);
	}

	@Test
	void 전체_앨범을_조회한다() {
		//given
		AlbumSearchRequest mockAlbumSearchRequest = getMockAlbumSearchRequest();
		Pageable mockPageable = SliceTestUtils.getMockPageable();
		User mockUser = UserTestUtils.getMockUser();
		Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		given(albumQueryService.findAlbumList(mockPageable, mockAlbumSearchRequest.getEmotionTagList(),
			mockAlbumSearchRequest.getSortType()))
			.willReturn(SliceTestUtils.getMockSlice(List.of(mockAlbum)));
		given(commentQueryService.getCommentCountByAlbumId(mockAlbum.getId())).willReturn(TestConst.TEST_COMMENT_COUNT);
		given(empathyQueryService.getEmpathyCountByAlbumId(mockAlbum.getId())).willReturn(TestConst.TEST_EMPATHY_COUNT);
		//when
		SliceResponse<AlbumSimpleResponse> albumList = albumReadUseCase.getAlbumList(mockPageable,
			mockAlbumSearchRequest);
		//then
		Assertions.assertThat(albumList.getContent().get(0).getCommentCount()).isEqualTo(TestConst.TEST_COMMENT_COUNT);
		Assertions.assertThat(albumList.getContent().get(0).getEmpathyCount()).isEqualTo(TestConst.TEST_EMPATHY_COUNT);
		Assertions.assertThat(albumList.getContent().get(0).getAlbumId()).isEqualTo(mockAlbum.getId());
		Assertions.assertThat(albumList.getContent().get(0).getTitle()).isEqualTo(mockAlbum.getTitle());
		Assertions.assertThat(albumList.getContent().get(0).getImageUrl())
			.isEqualTo(mockAlbum.getAlbumImages().get(0).getImageUrl());
	}

	@Test
	void 나의_앨범을_조회한다() {
		//given
		AlbumSearchRequest mockAlbumSearchRequest = getMockAlbumSearchRequest();
		Pageable mockPageable = SliceTestUtils.getMockPageable();
		User mockUser = UserTestUtils.getMockUser();
		Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		given(userUtils.getUser()).willReturn(mockUser);
		given(albumQueryService.findMyAlbumList(mockPageable, mockAlbumSearchRequest.getEmotionTagList(), mockAlbumSearchRequest.getSortType(), mockUser.getId()))
			.willReturn(SliceTestUtils.getMockSlice(List.of(mockAlbum)));
		given(commentQueryService.getCommentCountByAlbumId(mockAlbum.getId())).willReturn(TestConst.TEST_COMMENT_COUNT);
		given(empathyQueryService.getEmpathyCountByAlbumId(mockAlbum.getId())).willReturn(TestConst.TEST_EMPATHY_COUNT);
		//when
		SliceResponse<AlbumSimpleResponse> albumList = albumReadUseCase.getMyAlbumList(mockPageable,
			mockAlbumSearchRequest);
		//then
		Assertions.assertThat(albumList.getContent().get(0).getCommentCount()).isEqualTo(TestConst.TEST_COMMENT_COUNT);
		Assertions.assertThat(albumList.getContent().get(0).getEmpathyCount()).isEqualTo(TestConst.TEST_EMPATHY_COUNT);
		Assertions.assertThat(albumList.getContent().get(0).getAlbumId()).isEqualTo(mockAlbum.getId());
		Assertions.assertThat(albumList.getContent().get(0).getTitle()).isEqualTo(mockAlbum.getTitle());
		Assertions.assertThat(albumList.getContent().get(0).getImageUrl())
			.isEqualTo(mockAlbum.getAlbumImages().get(0).getImageUrl());
	}

	@Test
	void 앨범_상세_조회() {
		//given
		User mockUser = UserTestUtils.getMockUser();
		Album mockAlbum = AlbumTestUtils.getMockAlbum(mockUser);
		given(userUtils.getUser()).willReturn(mockUser);
		given(albumQueryService.findAlbumById(mockAlbum.getId())).willReturn(mockAlbum);
		given(commentQueryService.getCommentCountByAlbumId(mockAlbum.getId())).willReturn(TestConst.TEST_COMMENT_COUNT);
		given(empathyQueryService.getEmpathyCountByAlbumId(mockAlbum.getId())).willReturn(TestConst.TEST_EMPATHY_COUNT);
		//when
		AlbumInfoResponse albumInfoResponse = albumReadUseCase.getAlbum(mockAlbum.getId());
		//then
		Assertions.assertThat(albumInfoResponse.getImageUrlList()).isEqualTo(mockAlbum.getAlbumImages().stream().map(AlbumImage::getImageUrl).collect(Collectors.toList()));
		Assertions.assertThat(albumInfoResponse.getTitle()).isEqualTo(mockAlbum.getTitle());
		Assertions.assertThat(albumInfoResponse.getDescription()).isEqualTo(mockAlbum.getDescription());
		Assertions.assertThat(albumInfoResponse.getVisibility()).isEqualTo(mockAlbum.getVisibility());
		Assertions.assertThat(albumInfoResponse.getChangeable()).isEqualTo(Objects.equals(mockAlbum.getWriter().getId(), mockUser.getId()));
		Assertions.assertThat(albumInfoResponse.getWriter()).isEqualTo(mockUser.getNickname());
		Assertions.assertThat(albumInfoResponse.getPetName()).isEqualTo(mockUser.getMypet().getPetName());
		Assertions.assertThat(albumInfoResponse.getWriterProfileImageUrl()).isEqualTo(mockUser.getProfileImageUrl());
		Assertions.assertThat(albumInfoResponse.getAccessUserProfileImageUrl()).isEqualTo(mockUser.getProfileImageUrl());
		Assertions.assertThat(albumInfoResponse.getEmotionTagList()).isEqualTo(mockAlbum.getTags().stream().map(Tag::getEmotionTag).collect(Collectors.toList()));
		Assertions.assertThat(albumInfoResponse.getCommentCount()).isEqualTo(TestConst.TEST_COMMENT_COUNT);
		Assertions.assertThat(albumInfoResponse.getEmpathyCount()).isEqualTo(TestConst.TEST_EMPATHY_COUNT);
	}

	AlbumSearchRequest getMockAlbumSearchRequest() {
		return new AlbumSearchRequest(TestConst.EMOTION_TAG_LIST, TestConst.TEST_SORT_TYPE);
	}

}