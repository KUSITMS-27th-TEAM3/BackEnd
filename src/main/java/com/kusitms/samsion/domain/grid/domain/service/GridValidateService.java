package com.kusitms.samsion.domain.grid.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.grid.domain.exception.GridAlreadyRegisteredException;
import com.kusitms.samsion.domain.grid.domain.repository.GridRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class GridValidateService {

	private final GridRepository gridRepository;

	public void validateGridRegistered(Long userId) {
		if(gridRepository.existsGridByUserId(userId)) {
			throw new GridAlreadyRegisteredException(Error.GRID_ALREADY_REGISTERED);
		}
	}

}
