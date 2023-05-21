package com.kusitms.samsion.common.util;

import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;
import com.kusitms.samsion.domain.funeralshop.domain.entity.RunType;
import org.springframework.test.util.ReflectionTestUtils;

public class FuneralShopTestUtils {

    private static final FuneralShop funeralShop = setMockFuneralShopSingleton();
    public static FuneralShop getMockFuneralShop() { return funeralShop; }


    private static FuneralShop setMockFuneralShopSingleton() {
        FuneralShop funeralShop = FuneralShop.builder()
                .imageUrl(TestConst.TEST_SHOP_IMG_URL)
                .runType(RunType.FULL_TIME)
                .openTime(TestConst.TEST_OPENTIME)
                .closingTime(TestConst.TEST_CLOSINGTIME)
                .area(TestConst.TEST_AREA)
                .shopName(TestConst.TEST_SHOPNAME)
                .address(TestConst.TEST_ADDRESS)
                .phoneNumber(TestConst.TEST_PHONENUMBER)
                .url(TestConst.TEST_URL)
                .build();
        ReflectionTestUtils.setField(funeralShop, "id", TestConst.TEST_FUNERALSHOP_ID);
        return funeralShop;
    }

}
