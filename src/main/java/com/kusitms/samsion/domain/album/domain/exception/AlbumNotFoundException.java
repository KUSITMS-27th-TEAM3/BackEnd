package com.kusitms.samsion.domain.album.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class AlbumNotFoundException extends AlbumException{
	public AlbumNotFoundException(Error error) {
		super(error);
	}
}
