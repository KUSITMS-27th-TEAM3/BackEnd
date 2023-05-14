package com.kusitms.samsion.common.infrastructure.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.kusitms.samsion.domain.album.domain.entity.AlbumImage;
import com.kusitms.samsion.domain.album.domain.exception.FileDeleteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.kusitms.samsion.common.exception.Error;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3DeleteService {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void deleteImgList(List<AlbumImage> imgUrlList) {
        if (!imgUrlList.isEmpty()) {
            for (AlbumImage imgUrl : imgUrlList) {
                deleteImg(imgUrl.getImageUrl());
            }
        }
    }

    /*
    개별 이미지 삭제
    */
    public void deleteImg(String originImgUrl) {
        if (originImgUrl == null) return;
        try {
            amazonS3.deleteObject(bucket, originImgUrl.split("/")[3]);
        } catch (AmazonServiceException e) {
            throw new FileDeleteException(Error.FILE_DELETE_ERROR);
        }
    }
}
