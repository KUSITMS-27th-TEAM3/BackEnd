package com.kusitms.samsion.domain.funeralshop.application.service;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.FuneralShopTestUtils;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.domain.funeralshop.application.dto.request.FuneralShopSearchRequest;
import com.kusitms.samsion.domain.funeralshop.application.dto.response.FuneralShopInfoResponse;
import com.kusitms.samsion.domain.funeralshop.application.service.FuneralShopReadUseCase;
import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;
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

@ExtendWith(MockitoExtension.class)
@DisplayName("FuneralShopReadUseCase 테스트")
public class FuneralShopReadUseCaseTest {

    @Mock
    FuneralShopQueryService funeralShopQueryService;

    FuneralShopReadUseCase funeralShopReadUseCase;

    @BeforeEach
    void setUp(){
        funeralShopReadUseCase = new FuneralShopReadUseCase(funeralShopQueryService);
    }

    @Test
    void 장례_정보를_조회한다(){
        //given
        FuneralShop mockFuneralShop = FuneralShopTestUtils.getMockFuneralShop();
        List<FuneralShop> mockFuneralShopList = List.of(mockFuneralShop);
        Pageable mockPageable = SliceTestUtils.getMockPageable();
        Slice<FuneralShop> mockPage = SliceTestUtils.getMockSlice(mockFuneralShopList);
        List<String> areaTagList = Arrays.asList(TestConst.TEST_AREA);
        FuneralShopSearchRequest funeralShopSearchRequest = new FuneralShopSearchRequest(areaTagList);
        given(funeralShopQueryService.getFuneralShopList(mockPageable, areaTagList)).willReturn(mockPage);
        //when
        SliceResponse<FuneralShopInfoResponse> funeralShopList = funeralShopReadUseCase.getFuneralShopList(mockPageable, funeralShopSearchRequest);
        //then
        FuneralShopInfoResponse funeralShopInfoResponse = funeralShopList.getContent().get(0);
        Assertions.assertThat(funeralShopInfoResponse.getUrl()).isEqualTo(mockFuneralShop.getUrl());
        Assertions.assertThat(funeralShopInfoResponse.getShopName()).isEqualTo(mockFuneralShop.getShopName());
        Assertions.assertThat(funeralShopInfoResponse.getAddress()).isEqualTo(mockFuneralShop.getAddress());
        Assertions.assertThat(funeralShopInfoResponse.getArea()).isEqualTo(mockFuneralShop.getArea());
        Assertions.assertThat(funeralShopInfoResponse.getImgurl()).isEqualTo(mockFuneralShop.getImageUrl());
        Assertions.assertThat(funeralShopInfoResponse.getClosingTime()).isEqualTo(mockFuneralShop.getClosingTime());
        Assertions.assertThat(funeralShopInfoResponse.getOpenTime()).isEqualTo(mockFuneralShop.getOpenTime());
        Assertions.assertThat(funeralShopInfoResponse.getRuntype()).isEqualTo(mockFuneralShop.getRunType().toString());
    }

}
