package com.kusitms.samsion.domain.question.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.question.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	Optional<Answer> findByWriterIdAndQuestionId(Long userId, Long questionId);

	boolean existsByWriterIdAndQuestionId(Long userId, Long questionId);
}
