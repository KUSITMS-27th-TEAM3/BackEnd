package com.kusitms.samsion.domain.album.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.repository.AlbumImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@DomainService
@RequiredArgsConstructor
@Transactional
public class AlbumImageUpdateService {

    private final AlbumImageRepository albumImageRepository;

    public void updateAlbumImage(Album album, List<String> oldImageList, List<String> newImageList) {
        List<AlbumImage> albumImageList = new ArrayList<>();
        if(Objects.nonNull(oldImageList)) {
            albumImageList.addAll(oldImageList.stream()
                    .map(imageUrl -> new AlbumImage(imageUrl, album)).collect(Collectors.toList()));
        }
        if(Objects.nonNull(newImageList)) {
            albumImageList.addAll(newImageList.stream()
                    .map(imageUrl -> new AlbumImage(imageUrl, album)).collect(Collectors.toList()));
        }
        if(albumImageList.isEmpty()) return;
        albumImageRepository.saveAll(albumImageList);
    }
}
