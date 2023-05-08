package com.kusitms.samsion.presentation.album;

import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.application.album.dto.request.AlbumCreateRequest;
import com.kusitms.samsion.application.album.dto.response.AlbumInfoResponse;
import com.kusitms.samsion.application.album.dto.response.AlbumSimpleResponse;
import com.kusitms.samsion.application.album.service.AlbumCreateService;
import com.kusitms.samsion.application.album.service.AlbumReadService;
import com.kusitms.samsion.common.slice.SliceResponse;

import lombok.RequiredArgsConstructor;

/**
 * TODO : 공감, 댓글, 이미지 정보도 같이 가져와야 함
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

	private final AlbumReadService albumReadService;
	private final AlbumCreateService albumCreateService;

	@GetMapping
	public SliceResponse<AlbumSimpleResponse> getAlbumList(Pageable pageable){
		return albumReadService.getAlbumList(pageable);
	}

	@PostMapping()
	public AlbumInfoResponse createAlbum(@ModelAttribute AlbumCreateRequest request) throws
		ExecutionException,
		InterruptedException {
		return albumCreateService.createAlbum(request);
	}

	@GetMapping("/{albumId}")
	public AlbumInfoResponse getAlbum(@PathVariable Long albumId){
		return albumReadService.getAlbum(albumId);
	}

}
