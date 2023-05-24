package com.kusitms.samsion.common.util;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.user.domain.entity.User;

public class GridTestUtils {

    public static Grid getMockGrid(User mockUser) {
        Grid mockGrid = new Grid(TestConst.TEST_GRID_IMG_URL, mockUser);
        return mockGrid;
    }
}
