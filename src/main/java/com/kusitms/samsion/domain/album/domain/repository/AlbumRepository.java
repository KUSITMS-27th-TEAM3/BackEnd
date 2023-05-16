package com.kusitms.samsion.domain.album.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.album.domain.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>, AlbumRepositoryCustom {
}
