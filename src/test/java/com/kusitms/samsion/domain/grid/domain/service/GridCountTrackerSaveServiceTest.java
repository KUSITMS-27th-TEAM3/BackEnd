package com.kusitms.samsion.domain.grid.domain.service;

import com.kusitms.samsion.common.util.GridCountTrackerTestUtils;
import com.kusitms.samsion.common.util.GridTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.entity.GridCountTracker;
import com.kusitms.samsion.domain.grid.domain.repository.GridRedisRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("GridCountTrackerSaveService 테스트")
public class GridCountTrackerSaveServiceTest {

    @Mock
    GridRedisRepository gridRedisRepository;
    GridCountTrackerSaveService gridCountTrackerSaveService;

    @BeforeEach
    void setUp() { gridCountTrackerSaveService = new GridCountTrackerSaveService(gridRedisRepository); }

    @Test
    void 그리드_기록을_저장한다(){
        //given
        User mockUser = UserTestUtils.getMockUser();
        Grid mockGrid = GridTestUtils.getMockGrid(mockUser);
        GridCountTracker mockGridCountTracker = GridCountTrackerTestUtils.getGridCountTracker(mockGrid);
        //when
        gridCountTrackerSaveService.saveGridCountTracker(mockGridCountTracker);
        //then
        then(gridRedisRepository).should().save(mockGridCountTracker);
    }
}
