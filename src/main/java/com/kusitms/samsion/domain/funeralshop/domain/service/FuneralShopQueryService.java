package com.kusitms.samsion.domain.funeralshop.domain.service;

import com.kusitms.samsion.common.annotation.DomainService;

import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;
import com.kusitms.samsion.domain.funeralshop.domain.repository.FuneralShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class FuneralShopQueryService {

    private final FuneralShopRepository funeralShopRepository;

    public Slice<FuneralShop> getFuneralShopList(Pageable pageable, List<String> areaTagList){
        return funeralShopRepository.findFuneralShopList(pageable, areaTagList);
    }

}
