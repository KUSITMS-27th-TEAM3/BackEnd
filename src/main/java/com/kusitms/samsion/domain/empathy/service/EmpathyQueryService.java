package com.kusitms.samsion.domain.empathy.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.empathy.repository.EmpathyRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmpathyQueryService {

	private final EmpathyRepository empathyRepository;


	public boolean isEmpathyByUserIdAndAlbumId(Long userId, Long albumId){
		return empathyRepository.existsByUserIdAndAlbumId(albumId, userId);
	}

}
