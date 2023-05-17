package com.kusitms.samsion.domain.grid.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kusitms.samsion.domain.grid.domain.entity.GridCountTracker;

public interface GridRedisRepository extends CrudRepository<GridCountTracker, Long> {

	List<GridCountTracker> findByGridId(Long gridId);

}
