package com.kusitms.samsion.domain.empathy.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.empathy.domain.repository.EmpathyRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class EmpathyDeleteService {

	private final EmpathyRepository empathyRepository;

	public void deleteEmpathy(Long userId, Long albumId){
		empathyRepository.deleteByUserIdAndAlbumId(userId, albumId);
	}
}
