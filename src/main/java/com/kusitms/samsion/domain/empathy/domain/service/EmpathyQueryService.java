package com.kusitms.samsion.domain.empathy.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.empathy.domain.repository.EmpathyRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmpathyQueryService {

	private final EmpathyRepository empathyRepository;

	public boolean isEmpathyByUserIdAndAlbumId(Long userId, Long albumId){
		return empathyRepository.existsByUserIdAndAlbumId(userId, albumId);
	}

	public long getEmpathyCountByAlbumId(Long albumId){
		return empathyRepository.countByAlbumId(albumId);
	}

}
