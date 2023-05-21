package com.kusitms.samsion.domain.funeralshop.application.mapper;

import com.kusitms.samsion.domain.funeralshop.application.dto.response.FuneralShopInfoResponse;
import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;

public class FuneralShopMapper {

    public static FuneralShopInfoResponse toFuneralShopInfoResponse(FuneralShop funeralShop) {
        return FuneralShopInfoResponse.builder()
                .imgurl(funeralShop.getImageUrl())
                .runtype(funeralShop.getRunType().toString())
                .openTime(funeralShop.getOpenTime())
                .closingTime(funeralShop.getClosingTime())
                .area(funeralShop.getArea())
                .shopName(funeralShop.getShopName())
                .address(funeralShop.getAddress())
                .phoneNumber(funeralShop.getPhoneNumber())
                .url(funeralShop.getUrl())
                .build();
    }
}