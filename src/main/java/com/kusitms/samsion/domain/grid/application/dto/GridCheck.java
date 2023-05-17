package com.kusitms.samsion.domain.grid.application.dto;

import lombok.Getter;

@Getter
public class GridCheck {
	private final boolean isCheck;
	private final int gridNum;

	public GridCheck(boolean isCheck, int gridNum) {
		this.isCheck = isCheck;
		this.gridNum = gridNum;
	}
}
