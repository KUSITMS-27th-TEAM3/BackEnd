package com.kusitms.samsion.domain.album.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.album.domain.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	public void deleteAllByAlbumId(Long albumId);
}
