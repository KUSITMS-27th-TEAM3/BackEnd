package com.kusitms.samsion.domain.empathy.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.empathy.domain.entity.Empathy;

public interface EmpathyRepository extends JpaRepository<Empathy,Long> {

	Optional<Empathy> findByAlbumIdAndUserId(Long albumId, Long userId);

	boolean existsByUserIdAndAlbumId(Long userId, Long albumId);

	void deleteByUserIdAndAlbumId(Long userId, Long albumId);

	long countByAlbumId(Long albumId);
}
