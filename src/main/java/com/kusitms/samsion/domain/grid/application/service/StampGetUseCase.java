package com.kusitms.samsion.domain.grid.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.slice.ListResponse;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.grid.application.dto.response.StampResponse;
import com.kusitms.samsion.domain.grid.application.mapper.GridMapper;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.service.GridQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StampGetUseCase {

	private final UserUtils userUtils;
	private final GridQueryService gridQueryService;

	public ListResponse<StampResponse> getStampList(){
		Long userId = userUtils.getUser().getId();
		List<Grid> stampList = gridQueryService.findStampByUserId(userId);
		List<StampResponse> stampResponseList = GridMapper.mapToStampResponseList(stampList);
		return ListResponse.of(stampResponseList);
	}
}
