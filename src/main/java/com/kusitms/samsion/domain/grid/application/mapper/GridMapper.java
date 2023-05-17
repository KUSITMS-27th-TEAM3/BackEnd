package com.kusitms.samsion.domain.grid.application.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kusitms.samsion.common.annotation.Mapper;
import com.kusitms.samsion.domain.grid.application.dto.GridCheck;
import com.kusitms.samsion.domain.grid.application.dto.response.GridInfoResponse;
import com.kusitms.samsion.domain.grid.application.dto.response.StampResponse;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;

@Mapper
public class GridMapper {

	public static GridInfoResponse mapToGridInfoResponse(Grid grid) {
		final int gridCnt = grid.getGridCnt();
		List<GridCheck> gridCheckList = new ArrayList<>();
		for(int i =1; i< 60;i++){
			if(i<=gridCnt)
				gridCheckList.add(new GridCheck(true, i));
			else
				gridCheckList.add(new GridCheck(false, i));
		}

		return new GridInfoResponse(gridCheckList, grid.getGridImageUrl());
	}

	public static StampResponse mapToStampResponse(Grid grid) {
		return new StampResponse(grid.getId(), grid.getGridImageUrl());
	}

	public static List<StampResponse> mapToStampResponseList(List<Grid> stampList) {
		List<StampResponse> stampResponseList = stampList.stream()
			.map(GridMapper::mapToStampResponse)
			.collect(Collectors.toList());
		return stampResponseList;
	}
}
