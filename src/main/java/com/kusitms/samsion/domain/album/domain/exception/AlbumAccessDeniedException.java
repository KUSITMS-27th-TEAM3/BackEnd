package com.kusitms.samsion.domain.album.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class AlbumAccessDeniedException extends AlbumException{
	public AlbumAccessDeniedException(Error error) {
		super(error);
	}
}
