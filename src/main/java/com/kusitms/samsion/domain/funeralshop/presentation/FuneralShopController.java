package com.kusitms.samsion.domain.funeralshop.presentation;

import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.domain.funeralshop.application.dto.request.FuneralShopSearchRequest;
import com.kusitms.samsion.domain.funeralshop.application.dto.response.FuneralShopInfoResponse;
import com.kusitms.samsion.domain.funeralshop.application.service.FuneralShopReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funeral")
@RequiredArgsConstructor
public class FuneralShopController {
    private final FuneralShopReadUseCase funeralShopReadUseCase;

    @GetMapping
    public SliceResponse<FuneralShopInfoResponse> getFuneralShopList(Pageable pageable, FuneralShopSearchRequest request){
        return funeralShopReadUseCase.getFuneralShopList(pageable, request);
    }


}
