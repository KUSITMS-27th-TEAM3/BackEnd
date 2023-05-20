package com.kusitms.samsion.domain.grid.application.handler;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.domain.grid.application.dto.request.GridCreateRequest;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.service.GridSaveService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import com.kusitms.samsion.domain.user.domain.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class GridCreateHandler {

	private final UserQueryService userQueryService;
	private final GridSaveService gridSaveService;

	@TransactionalEventListener
	public void createGrid(final GridCreateRequest request) {
		User user = userQueryService.findById(request.getUserId());
		final String petImageUrl = user.getMypet().getPetImageUrl();
		final Grid grid = new Grid(petImageUrl, user);
		gridSaveService.saveGrid(grid);
	}
}
