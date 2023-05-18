package com.kusitms.samsion.domain.grid.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.grid.domain.entity.GridCountTracker;
import com.kusitms.samsion.domain.grid.domain.repository.GridRedisRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@DomainService
@RequiredArgsConstructor
public class GridCountTrackerQueryService {

	private final GridRedisRepository gridRedisRepository;

	public Optional<GridCountTracker> findGridCountByGridId(Long gridId) {
		List<GridCountTracker> gridCountTrackerList = gridRedisRepository.findByGridId(gridId);
		if(gridCountTrackerList.size() == 0) {
			return Optional.empty();
		}

		return gridCountTrackerList.stream()
			.filter(gridCountTracker -> gridCountTracker.getCreatedDate().isAfter(LocalDate.now().atStartOfDay()))
			.findFirst();
	}

}
