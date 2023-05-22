package com.kusitms.samsion.domain.funeralshop.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.domain.funeralshop.application.dto.request.FuneralShopSearchRequest;
import com.kusitms.samsion.domain.funeralshop.application.dto.response.FuneralShopInfoResponse;
import com.kusitms.samsion.domain.funeralshop.application.mapper.FuneralShopMapper;
import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;
import com.kusitms.samsion.domain.funeralshop.domain.service.FuneralShopQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@UseCase
@RequiredArgsConstructor
public class FuneralShopReadUseCase {

    private final FuneralShopQueryService funeralShopQueryService;

    public SliceResponse<FuneralShopInfoResponse> getFuneralShopList(Pageable pageable, FuneralShopSearchRequest request) {

        Slice<FuneralShop> funeralShopList = funeralShopQueryService.getFuneralShopList(pageable, request.getAreaTagList());

        Slice<FuneralShopInfoResponse> funeralShopInfoResponses = funeralShopList.map(funeralShop -> {
            return FuneralShopMapper.toFuneralShopInfoResponse(funeralShop);
        });
        return SliceResponse.of(funeralShopInfoResponses);
    }
}
