package com.kusitms.samsion.domain.album.exception;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.Error;

public class AlbumException extends BusinessException {
	public AlbumException(Error error) {
		super(error);
	}
}
