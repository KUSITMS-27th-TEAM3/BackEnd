package com.kusitms.samsion.domain.album.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;

public interface AlbumImageRepository extends JpaRepository<AlbumImage, Long> {

	public void deleteAllByAlbumId(Long albumId);
}
