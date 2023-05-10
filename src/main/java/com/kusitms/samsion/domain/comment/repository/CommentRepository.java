package com.kusitms.samsion.domain.comment.repository;

import com.kusitms.samsion.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

