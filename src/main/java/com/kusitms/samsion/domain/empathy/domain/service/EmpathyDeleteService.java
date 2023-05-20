package com.kusitms.samsion.domain.empathy.domain.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.consts.CachingStoreConst;
import com.kusitms.samsion.domain.empathy.domain.repository.EmpathyRepository;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class EmpathyDeleteService {

	private final EmpathyRepository empathyRepository;

	@CacheEvict(value = CachingStoreConst.EMPATHY_COUNT_CACHE_NAME, key = "#comment.albumId")
	public void deleteEmpathy(Long userId, Long albumId){
		empathyRepository.deleteByUserIdAndAlbumId(userId, albumId);
	}
}
