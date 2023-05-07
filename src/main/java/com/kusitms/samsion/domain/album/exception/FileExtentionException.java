package com.kusitms.samsion.domain.album.exception;

import com.kusitms.samsion.common.exception.Error;

public class FileExtentionException extends AlbumException{
    public FileExtentionException(Error error) {
        super(error);
    }
}
