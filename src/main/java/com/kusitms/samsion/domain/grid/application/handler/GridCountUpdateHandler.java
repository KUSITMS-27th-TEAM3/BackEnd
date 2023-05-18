package com.kusitms.samsion.domain.grid.application.handler;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.domain.grid.application.dto.request.GridCountUpdateRequest;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.entity.GridCountTracker;
import com.kusitms.samsion.domain.grid.domain.exception.GridNotRegisteredException;
import com.kusitms.samsion.domain.grid.domain.service.GridCountTrackerQueryService;
import com.kusitms.samsion.domain.grid.domain.service.GridCountTrackerSaveService;
import com.kusitms.samsion.domain.grid.domain.service.GridQueryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Slf4j
@RequiredArgsConstructor
public class GridCountUpdateHandler {

	private final GridQueryService gridQueryService;
	private final GridCountTrackerQueryService gridCountTrackerQueryService;
	private final GridCountTrackerSaveService gridCountTrackerSaveService;

	@TransactionalEventListener
	public void updateGridCount(GridCountUpdateRequest request) {
		try {
			Grid grid = gridQueryService.findGridByUserId(request.getUserId());
			Optional<GridCountTracker> gridCountTracker = gridCountTrackerQueryService.findGridCountByGridId(grid.getId());
			if (gridCountTracker.isEmpty()) {
				gridCountTrackerSaveService.saveGridCountTracker(new GridCountTracker(grid.getId(), LocalDateTime.now()));
				grid.incGridCnt();
			}
		} catch (GridNotRegisteredException ignored){}
	}

}
