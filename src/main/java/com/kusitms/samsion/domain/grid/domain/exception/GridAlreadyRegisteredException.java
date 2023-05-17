package com.kusitms.samsion.domain.grid.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class GridAlreadyRegisteredException extends GridException {
	public GridAlreadyRegisteredException(Error error) {
		super(error);
	}
}
