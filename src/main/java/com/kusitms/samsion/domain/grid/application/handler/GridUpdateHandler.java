package com.kusitms.samsion.domain.grid.application.handler;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.domain.grid.application.dto.request.GridUpdateRequest;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.service.GridQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@UseCase
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class GridUpdateHandler {

	private final GridQueryService gridQueryService;

	@TransactionalEventListener
	public void handleGridUpdateEvent(GridUpdateRequest request) {
		Grid grid = gridQueryService.findGridByUserId(request.getUserId());
		grid.updateGridImageUrl(request.getPetImageUrl());
	}
}
