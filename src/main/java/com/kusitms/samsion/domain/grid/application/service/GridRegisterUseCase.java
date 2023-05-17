package com.kusitms.samsion.domain.grid.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.infrastructure.s3.S3UploadService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.grid.application.dto.request.GridRegisterRequest;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.service.GridSaveService;
import com.kusitms.samsion.domain.grid.domain.service.GridValidateService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class GridRegisterUseCase {

	private final UserUtils userUtils;
	private final GridSaveService gridSaveService;
	private final GridValidateService gridValidateService;
	private final S3UploadService s3UploadService;

	public void registerGrid(GridRegisterRequest request) {
		User user = userUtils.getUser();
		gridValidateService.validateGridRegistered(user.getId());
		final String gridImageUrl = s3UploadService.uploadImg(request.getGridImage());
		gridSaveService.saveGrid(new Grid(gridImageUrl, user));
	}
}
