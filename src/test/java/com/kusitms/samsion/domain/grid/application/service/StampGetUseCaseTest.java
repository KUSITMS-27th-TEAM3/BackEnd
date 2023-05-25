package com.kusitms.samsion.domain.grid.application.service;

import com.kusitms.samsion.common.slice.ListResponse;
import com.kusitms.samsion.common.util.GridTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.grid.application.dto.response.StampResponse;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("StampGetUseCase 테스트")
public class StampGetUseCaseTest {

    @Mock
    UserUtils userUtils;
    @Mock
    GridQueryService gridQueryService;
    StampGetUseCase stampGetUseCase;

    @BeforeEach
    void setUp() { stampGetUseCase = new StampGetUseCase(userUtils, gridQueryService); }

    @Test
    void 스탬프를_조회한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Grid mockGrid = GridTestUtils.getMockGrid(mockUser);
        List<Grid> stampList = new ArrayList<>();
        stampList.add(mockGrid);
        given(userUtils.getUser()).willReturn(mockUser);
        given(gridQueryService.findStampByUserId(mockUser.getId())).willReturn(stampList);

        //when
        ListResponse<StampResponse> stampResponseListResponse = stampGetUseCase.getStampList();

        //then
        Assertions.assertThat(stampResponseListResponse).isNotNull();
        Assertions.assertThat(stampResponseListResponse.getContent().get(0).getImageUrl()).isEqualTo(mockGrid.getGridImageUrl());
    }
}
