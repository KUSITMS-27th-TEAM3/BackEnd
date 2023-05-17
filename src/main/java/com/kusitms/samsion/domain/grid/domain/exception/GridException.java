package com.kusitms.samsion.domain.grid.domain.exception;

import com.kusitms.samsion.common.exception.BusinessException;
import com.kusitms.samsion.common.exception.Error;

public class GridException extends BusinessException {
	public GridException(Error error) {
		super(error);
	}
}
