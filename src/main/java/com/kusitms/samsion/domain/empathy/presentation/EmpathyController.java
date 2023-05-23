package com.kusitms.samsion.domain.empathy.presentation;

import com.kusitms.samsion.domain.empathy.application.dto.response.EmpathyCountResponse;
import com.kusitms.samsion.domain.empathy.application.service.EmpathyCountUseCase;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.common.consts.CachingStoreConst;
import com.kusitms.samsion.domain.empathy.application.service.EmpathyToggleUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmpathyController {

	private final EmpathyToggleUseCase empathyToggleUseCase;
	private final EmpathyCountUseCase empathyCountUseCase;

	@CacheEvict(value = CachingStoreConst.EMPATHY_COUNT_CACHE_NAME, key = "#albumId")
	@GetMapping("/album/{albumId}/empathy")
	public void addEmpathy(@PathVariable Long albumId) {
		empathyToggleUseCase.toggleEmpathy(albumId);
	}

	@GetMapping("/album/{albumId}/empathy/count")
	public EmpathyCountResponse getEmpathyCount(@PathVariable Long albumId) {
		return empathyCountUseCase.getEmpathyCount(albumId);
	}

}