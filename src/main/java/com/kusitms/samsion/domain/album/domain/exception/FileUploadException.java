package com.kusitms.samsion.domain.album.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class FileUploadException extends AlbumException{
    public FileUploadException(Error error) {
        super(error);
    }
}
