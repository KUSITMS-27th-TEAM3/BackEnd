package com.kusitms.samsion.domain.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.album.entity.AlbumImage;

public interface AlbumImageRepository extends JpaRepository<AlbumImage, Long> {
}
