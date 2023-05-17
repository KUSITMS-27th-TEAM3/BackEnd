package com.kusitms.samsion.domain.grid.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.grid.domain.entity.GridCountTracker;
import com.kusitms.samsion.domain.grid.domain.repository.GridRedisRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class GridCountTrackerSaveService {

	private final GridRedisRepository gridRedisRepository;

	public void saveGridCountTracker(GridCountTracker gridCountTracker) {
		gridRedisRepository.save(gridCountTracker);
	}
}
