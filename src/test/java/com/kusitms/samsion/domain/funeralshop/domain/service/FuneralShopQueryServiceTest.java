package com.kusitms.samsion.domain.funeralshop.domain.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.util.FuneralShopTestUtils;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;
import com.kusitms.samsion.domain.funeralshop.domain.repository.FuneralShopRepository;
import com.kusitms.samsion.domain.funeralshop.domain.service.FuneralShopQueryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("FuneralShopQueryService 테스트")
public class FuneralShopQueryServiceTest {

    @Mock
    FuneralShopRepository funeralShopRepository;
    FuneralShopQueryService funeralShopQueryService;

    @BeforeEach
    void setUp() { funeralShopQueryService = new FuneralShopQueryService(funeralShopRepository);}

    @Test
    void 장례_정보를_조회_요청을_받는다() {
        //given
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        List<String> areaTagList = Arrays.asList(TestConst.TEST_AREA);
        FuneralShop mockFuneralShop = FuneralShopTestUtils.getMockFuneralShop();
        List<FuneralShop> mockFuneralShopList = List.of(mockFuneralShop);
        Slice<FuneralShop> mockPage = SliceTestUtils.getMockSlice(mockFuneralShopList);
        given(funeralShopRepository.findFuneralShopList(mockPageable, areaTagList)).willReturn(mockPage);
        //when
        Slice<FuneralShop> funeralShopList =  funeralShopQueryService.getFuneralShopList(mockPageable, areaTagList);
        //then
        Assertions.assertThat(funeralShopList).isNotNull();
        Assertions.assertThat(funeralShopList).usingRecursiveComparison().isEqualTo(mockPage);
        then(funeralShopRepository).should(times(1)).findFuneralShopList(mockPageable, areaTagList);
    }
}

