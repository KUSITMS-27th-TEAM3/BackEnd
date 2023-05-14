package com.kusitms.samsion.domain.album.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class FileDeleteException extends AlbumException{
    public FileDeleteException(Error error) {
        super(error);
    }
}
