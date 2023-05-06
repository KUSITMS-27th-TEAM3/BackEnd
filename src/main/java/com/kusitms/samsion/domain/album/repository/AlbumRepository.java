package com.kusitms.samsion.domain.album.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.album.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

	Slice<Album> findAllBy(Pageable pageable);
}
