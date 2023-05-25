package com.kusitms.samsion.domain.grid.domain.service;

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
import java.util.Optional;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("GridCountTrackerQueryService 테스트")
public class GridCountTrackerQueryServiceTest {

    @Mock
    GridRedisRepository gridRedisRepository;
    GridCountTrackerQueryService gridCountTrackerQueryService;

    @BeforeEach
    void setUp() { gridCountTrackerQueryService = new GridCountTrackerQueryService(gridRedisRepository); }

    @Test
    void 그리드_기록_조회_요청을_받는다(){
        //given
        User mockUser = UserTestUtils.getMockUser();
        Grid mockGrid = GridTestUtils.getMockGrid(mockUser);
        //when
        Optional<GridCountTracker> gridCountTracker = gridCountTrackerQueryService.findGridCountByGridId(mockGrid.getId());
        //then
        then(gridRedisRepository).should(times(1)).findByGridId(mockGrid.getId());
    }

}
