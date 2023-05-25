package com.kusitms.samsion.domain.album.presentation;

import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumSearchRequest;
import com.kusitms.samsion.domain.album.application.dto.request.AlbumUpdateRequest;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.domain.album.application.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.domain.album.application.service.AlbumCreateUseCase;
import com.kusitms.samsion.domain.album.application.service.AlbumDeleteUseCase;
import com.kusitms.samsion.domain.album.application.service.AlbumReadUseCase;
import com.kusitms.samsion.domain.album.application.service.AlbumUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

	private final AlbumReadUseCase albumReadUseCase;
	private final AlbumCreateUseCase albumCreateUseCase;
	private final AlbumUpdateUseCase albumUpdateUseCase;
	private final AlbumDeleteUseCase albumDeleteUseCase;

	/**
	 * 기존 jpa : 앨범 10개 조회 기준, 3회 warmup, 4회 테스트 평균 853ms
	 * QueryDsl : 앨범 10개 조회 기준, 3회 warmup, 4회 테스트 평균 347ms
	 *
	 * 캐시 미적용 : 3회 warmup 4회 테스트 평균 200ms
	 * 캐시 적용(댓글, 공감 수 캐싱) : 3회 warmup 4회 테스트 평균 150ms
	 */
	@GetMapping
	public SliceResponse<AlbumSimpleResponse> getAlbumList(Pageable pageable, @ModelAttribute AlbumSearchRequest request){
		return albumReadUseCase.getAlbumList(pageable, request);
	}

	@GetMapping("/private")
	public SliceResponse<AlbumSimpleResponse> getMyAlbumList(Pageable pageable, @ModelAttribute AlbumSearchRequest request){
		return albumReadUseCase.getMyAlbumList(pageable, request);
	}

	@PostMapping()
	public AlbumInfoResponse createAlbum(@ModelAttribute AlbumCreateRequest request){
		return albumCreateUseCase.createAlbum(request);
	}

	/**
	 * 캐시 미적용 : 3회 warmup 4회 테스트 평균 40ms
	 * 캐시 적용 : 3회 warmup 4회 테스트 평균 20ms
	 */
	@GetMapping("/{albumId}")
	public AlbumInfoResponse getAlbum(@PathVariable Long albumId){
		return albumReadUseCase.getAlbum(albumId);
	}

	/**
	 * 비동기 실행 : 100회 요청 4s82ms
	 * 동기 실행 : 100회
	 */
	@PostMapping("/{albumId}")
	public void updateAlbum(@PathVariable Long albumId, @ModelAttribute AlbumUpdateRequest request){
		albumUpdateUseCase.updateAlbum(albumId, request);
	}

	@DeleteMapping("/{albumId}")
	public void deleteAlbum(@PathVariable Long albumId) {
		albumDeleteUseCase.deleteAlbum(albumId);
	}

}
