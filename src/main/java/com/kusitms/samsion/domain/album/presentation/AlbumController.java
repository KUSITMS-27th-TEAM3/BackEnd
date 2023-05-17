package com.kusitms.samsion.domain.album.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumSearchRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.application.service.AlbumCreateUseCase;
import com.kusitms.samsion.domain.album.application.service.AlbumReadUseCase;

import lombok.RequiredArgsConstructor;

/**
 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

	private final AlbumReadUseCase albumReadUseCase;
	private final AlbumCreateUseCase albumCreateUseCase;

	/**
	 * 기존 jpa : 앨범 10개 조회 기준, 3회 warmup, 4회 테스트 평균 853ms
	 * QueryDsl : 앨범 10개 조회 기준, 3회 warmup, 4회 테스트 평균 347ms
	 */
	@GetMapping
	public SliceResponse<AlbumSimpleResponse> getAlbumList(Pageable pageable, @ModelAttribute AlbumSearchRequest request){
		return albumReadUseCase.getAlbumList(pageable, request);
	}

	@GetMapping("/private")
	public SliceResponse<AlbumSimpleResponse> getMyAlbumList(Pageable pageable, @ModelAttribute AlbumSearchRequest request){
		return albumReadUseCase.getMyAlbumList(pageable, request);
	}


	/**
	 * 이미지 2개, 태그 5개 기준으로 생성시간 3회 warmup, 4회 테스트 진행시 평균 1000ms
	 *
	 */
	@PostMapping()
	public AlbumInfoResponse createAlbum(@ModelAttribute AlbumCreateRequest request){
		return albumCreateUseCase.createAlbum(request);
	}

	@GetMapping("/{albumId}")
	public AlbumInfoResponse getAlbum(@PathVariable Long albumId){
		return albumReadUseCase.getAlbum(albumId);
	}

}
