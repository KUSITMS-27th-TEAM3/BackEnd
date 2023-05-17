package com.kusitms.samsion.domain.grid.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.grid.application.dto.response.GridInfoResponse;
import com.kusitms.samsion.domain.grid.application.mapper.GridMapper;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.service.GridQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GridGetUseCase {

	private final UserUtils userUtils;
	private final GridQueryService gridQueryService;

	public GridInfoResponse getGrid(){
		User user = userUtils.getUser();
		Grid grid = gridQueryService.findGridByUserId(user.getId());
		return GridMapper.mapToGridInfoResponse(grid);
	}
}
