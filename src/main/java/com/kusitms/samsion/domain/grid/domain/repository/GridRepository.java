package com.kusitms.samsion.domain.grid.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kusitms.samsion.domain.grid.domain.entity.Grid;

public interface GridRepository extends JpaRepository<Grid, Long> {

	@Query("select g from Grid g where g.user.id = :userId and g.gridStatus = 'GRID'")
	Optional<Grid> findGridByUserId(@Param("userId") Long userId);

	@Query("select g from Grid g where g.user.id = :userId and g.gridStatus = 'STAMP' and g.gridCnt = 60")
	List<Grid> findStampListByUserId(@Param("userId") Long userId);

	@Query("select count (g) > 0 from Grid g where g.user.id = :userId and g.gridStatus = 'GRID'")
	boolean existsGridByUserId(Long userId);
}
