package com.kusitms.samsion.domain.grid.domain.service;

import com.kusitms.samsion.common.util.GridTestUtils;
import com.kusitms.samsion.common.util.UserTestUtils;
import com.kusitms.samsion.domain.grid.domain.entity.Grid;
import com.kusitms.samsion.domain.grid.domain.exception.GridNotRegisteredException;
import com.kusitms.samsion.domain.grid.domain.repository.GridRepository;
import com.kusitms.samsion.domain.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("GridQueryService 테스트")
public class GridQueryServiceTest {

    @Mock
    GridRepository gridRepository;
    GridQueryService gridQueryService;

    @BeforeEach
    void setUp(){ gridQueryService = new GridQueryService(gridRepository); }

    @Nested
    class findGridByUserId_메서드에서 {
        @Test
        void 그리드가_존재하면_반환한다() {
            //given
            User mockUser = UserTestUtils.getMockUser();
            Grid mockGrid = GridTestUtils.getMockGrid(mockUser);
            given(gridRepository.findGridByUserId(mockUser.getId())).willReturn(Optional.of(mockGrid));

            //when
            Grid grid = gridQueryService.findGridByUserId(mockUser.getId());

            //then
            Assertions.assertThat(grid).usingRecursiveComparison().isEqualTo(mockGrid);
        }

        @Test
        void 그리드가_존재하지_않으면_예외가_발생한다() {
            //given
            User mockUser = UserTestUtils.getMockUser();
            given(gridRepository.findGridByUserId(mockUser.getId())).willReturn(Optional.empty());
            //when
            //then
            Assertions.assertThatThrownBy(() -> gridQueryService.findGridByUserId(mockUser.getId()))
                    .isInstanceOf(GridNotRegisteredException.class);
        }

    }
    @Test
    void 스탬프를_조회한다() {
        //given
        User mockUser = UserTestUtils.getMockUser();
        Grid mockGrid = GridTestUtils.getMockGrid(mockUser);
        given(gridRepository.findStampListByUserId(mockUser.getId())).willReturn(List.of(mockGrid));

        //when
        List<Grid> gridList = gridQueryService.findStampByUserId(mockUser.getId());

        //then
        Assertions.assertThat(gridList.get(0)).usingRecursiveComparison().isEqualTo(mockGrid);
    }

}
