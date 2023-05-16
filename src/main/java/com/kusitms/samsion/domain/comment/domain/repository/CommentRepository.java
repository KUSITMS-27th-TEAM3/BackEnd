package com.kusitms.samsion.domain.comment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.comment.domain.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	long countByAlbumId(Long albumId);
}

