package com.kusitms.samsion.domain.comment.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {


	long countByAlbumIdAndDeletedFalse(Long albumId);
	@Query("select c from Comment c where c.album.id = :albumId order by c.parent.id asc nulls first, c.createdDate asc")
	Slice<Comment> findByAlbumId(Pageable pageable, @Param("albumId") Long albumId);
}

