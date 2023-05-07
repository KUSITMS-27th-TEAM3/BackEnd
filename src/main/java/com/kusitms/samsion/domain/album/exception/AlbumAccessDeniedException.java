package com.kusitms.samsion.domain.album.exception;

import com.kusitms.samsion.common.exception.Error;

public class AlbumAccessDeniedException extends AlbumException{
	public AlbumAccessDeniedException(Error error) {
		super(error);
	}
}
