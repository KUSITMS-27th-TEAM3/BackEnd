package com.kusitms.samsion.domain.question.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kusitms.samsion.domain.question.domain.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query(value = "select q from Question q join fetch q.answers a where a.writer.id = :userId",
		countQuery = "select count(q) from Question q")
	Page<Question> findAll(Pageable pageable, @Param("userId") Long userId);

}
