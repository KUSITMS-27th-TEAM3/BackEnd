package com.kusitms.samsion.domain.grid.domain.service;

import com.kusitms.samsion.common.util.GridTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.repository.GridRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("GridSaveService 테스트")
public class GridSaveServiceTest {

    @Mock
    GridRepository gridRepository;
    GridSaveService gridSaveService;

    @BeforeEach
    void setUp() { gridSaveService = new GridSaveService(gridRepository); }

    @Test
    void 그리드를_저장한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Grid mockGrid = GridTestUtils.getMockGrid(mockUser);
        //when
        gridSaveService.saveGrid(mockGrid);
        //then
        then(gridRepository).should().save(mockGrid);
    }
}
