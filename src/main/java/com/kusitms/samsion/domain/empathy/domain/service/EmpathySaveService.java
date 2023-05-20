package com.kusitms.samsion.domain.empathy.domain.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.common.consts.CachingStoreConst;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.empathy.domain.entity.Empathy;
import com.kusitms.samsion.domain.empathy.domain.repository.EmpathyRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class EmpathySaveService {

	private final EmpathyRepository empathyRepository;

	@CacheEvict(value = CachingStoreConst.EMPATHY_COUNT_CACHE_NAME, key = "#comment.albumId")
	public void saveEmpathy(User user, Album album){
		final Empathy empathy = new Empathy(user, album);
		empathyRepository.save(empathy);
	}

}
