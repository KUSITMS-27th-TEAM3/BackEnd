package com.kusitms.samsion.domain.funeralshop.domain.repository;

import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FuneralShopRepositoryCustom {
    Slice<FuneralShop> findFuneralShopList(Pageable pageable, List<String> areaTagList);
}
