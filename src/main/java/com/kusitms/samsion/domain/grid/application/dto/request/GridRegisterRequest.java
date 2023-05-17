package com.kusitms.samsion.domain.grid.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class GridRegisterRequest {

	private MultipartFile gridImage;

	public GridRegisterRequest(MultipartFile gridImage) {
		this.gridImage = gridImage;
	}
}
