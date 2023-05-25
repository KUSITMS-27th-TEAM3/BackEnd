package com.kusitms.samsion.domain.grid.application.service;

import com.kusitms.samsion.common.util.GridTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.grid.application.dto.response.GridInfoResponse;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.service.GridQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("GridGetUseCase 테스트")
public class GridGetUseCaseTest {

    @Mock
    UserUtils userUtils;
    @Mock
    GridQueryService gridQueryService;
    GridGetUseCase gridGetUseCase;

    @BeforeEach
    void setUp() {
        gridGetUseCase = new GridGetUseCase(userUtils, gridQueryService);
    }

    @Test
    void 그리드를_조회한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Grid mockGrid = GridTestUtils.getMockGrid(mockUser);
        given(userUtils.getUser()).willReturn(mockUser);
        given(gridQueryService.findGridByUserId(mockUser.getId())).willReturn(mockGrid);

        //when
        GridInfoResponse gridInfoResponse = gridGetUseCase.getGrid();

        //then
        Assertions.assertThat(gridInfoResponse).isNotNull();
        Assertions.assertThat(gridInfoResponse.getGridCheckList().size()).isEqualTo(59);
        Assertions.assertThat(gridInfoResponse.getGridImageUrl()).isEqualTo(mockGrid.getGridImageUrl());
    }
}
