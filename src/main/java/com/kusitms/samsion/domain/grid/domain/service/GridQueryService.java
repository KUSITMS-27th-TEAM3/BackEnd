package com.kusitms.samsion.domain.grid.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.exception.GridNotRegisteredException;
import com.kusitms.samsion.domain.grid.domain.repository.GridRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@DomainService
@RequiredArgsConstructor
public class GridQueryService {

	private final GridRepository gridRepository;

	public Grid findGridByUserId(Long userId) {
		return gridRepository.findGridByUserId(userId)
			.orElseThrow(()-> new GridNotRegisteredException(Error.GRID_NOT_REGISTERED));
	}

	public boolean isGridRegistered(Long userId) {
		return gridRepository.existsGridByUserId(userId);
	}

	public List<Grid> findStampByUserId(Long userId){
		return gridRepository.findStampListByUserId(userId);
	}
}
