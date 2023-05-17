package com.kusitms.samsion.domain.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.user.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long userId);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
