package com.kusitms.samsion.domain.funeralshop.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FuneralShopInfoResponse {

    private final String imgurl;

    private final String runtype;

    private final String openTime;

    private final String closingTime;

    private final String area;

    private final String shopName;

    private final String address;

    private final String phoneNumber;

    private final String url;

    @Builder
    public FuneralShopInfoResponse(String imgurl, String runtype, String openTime, String closingTime,
                                   String area, String shopName, String address, String phoneNumber, String url) {
        this.imgurl = imgurl;
        this.runtype = runtype;
        this.openTime = openTime;
        this.closingTime = closingTime;
        this.area = area;
        this.shopName = shopName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.url = url;
    }
}
