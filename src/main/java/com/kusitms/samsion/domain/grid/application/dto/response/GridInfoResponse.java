package com.kusitms.samsion.domain.grid.application.dto.response;

import java.util.List;

import com.kusitms.samsion.domain.grid.application.dto.GridCheck;

import lombok.Getter;

@Getter
public class GridInfoResponse {

	private final List<GridCheck> gridCheckList;
	private final String gridImageUrl;

	public GridInfoResponse(List<GridCheck> gridCheckList, String gridImageUrl) {
		this.gridCheckList = gridCheckList;
		this.gridImageUrl = gridImageUrl;
	}
}
