package com.kusitms.samsion.domain.grid.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.repository.GridRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class GridSaveService {

	private final GridRepository gridRepository;

	public void saveGrid(Grid grid) {
		gridRepository.save(grid);
	}
}
