package com.kusitms.samsion.domain.grid.domain.exception;

import com.kusitms.samsion.common.exception.Error;

public class GridNotRegisteredException extends GridException {

	public GridNotRegisteredException(Error error) {
		super(error);
	}
}
