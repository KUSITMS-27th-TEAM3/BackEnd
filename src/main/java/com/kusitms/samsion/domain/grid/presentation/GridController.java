package com.kusitms.samsion.domain.grid.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.common.slice.ListResponse;
import com.kusitms.samsion.domain.grid.application.dto.response.GridInfoResponse;
import com.kusitms.samsion.domain.grid.application.dto.response.StampResponse;
import com.kusitms.samsion.domain.grid.application.service.GridGetUseCase;
import com.kusitms.samsion.domain.grid.application.service.StampGetUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/grid")
public class GridController {

	private final GridGetUseCase gridGetUseCase;
	private final StampGetUseCase stampGetUseCase;

	@GetMapping
	public GridInfoResponse getGrid() {
		return gridGetUseCase.getGrid();
	}

	@GetMapping("/stamp")
	public ListResponse<StampResponse> getStamp(){
		return stampGetUseCase.getStampList();
	}

}
