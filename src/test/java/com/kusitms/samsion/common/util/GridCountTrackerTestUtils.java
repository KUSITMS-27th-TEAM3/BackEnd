package com.kusitms.samsion.common.util;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.entity.GridCountTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GridCountTrackerTestUtils {
    public static GridCountTracker getGridCountTracker(Grid mockGrid) {
        GridCountTracker mockGridCountTracker = new GridCountTracker(mockGrid.getId(),
                LocalDateTime.parse(TestConst.TEST_GRID_CREATED_DATE, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return mockGridCountTracker;
    }
}
