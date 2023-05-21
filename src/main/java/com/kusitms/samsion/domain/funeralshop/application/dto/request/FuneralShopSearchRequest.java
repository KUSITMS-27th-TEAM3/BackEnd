package com.kusitms.samsion.domain.funeralshop.application.dto.request;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
public class FuneralShopSearchRequest {

    @Nullable
    private final List<String> areaTagList;

    public FuneralShopSearchRequest(List<String> areaTagList) {
        this.areaTagList = areaTagList;
    }
}
