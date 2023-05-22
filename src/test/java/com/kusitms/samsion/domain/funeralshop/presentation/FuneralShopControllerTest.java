package com.kusitms.samsion.domain.funeralshop.presentation;

import com.kusitms.samsion.common.config.CommonRestDocs;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.slice.SliceResponse;
import com.kusitms.samsion.common.util.SliceTestUtils;
import com.kusitms.samsion.domain.funeralshop.application.dto.response.FuneralShopInfoResponse;
import com.kusitms.samsion.domain.funeralshop.application.service.FuneralShopReadUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FuneralShopController.class)
@DisplayName("FuneralShopController 테스트")
public class FuneralShopControllerTest extends CommonRestDocs {

    @MockBean
    FuneralShopReadUseCase funeralShopReadUseCase;

    @Test
    void 장례정보_조회() throws Exception {
        //given
        FuneralShopInfoResponse funeralShopInfoResponse = FuneralShopInfoResponse.builder()
                .imgurl(TestConst.TEST_SHOP_IMG_URL)
                .runtype(TestConst.TEST_RUN_TYPE)
                .openTime(TestConst.TEST_OPENTIME)
                .closingTime(TestConst.TEST_CLOSINGTIME)
                .area(TestConst.TEST_AREA)
                .shopName(TestConst.TEST_SHOPNAME)
                .address(TestConst.TEST_ADDRESS)
                .phoneNumber(TestConst.TEST_PHONENUMBER)
                .url(TestConst.TEST_URL)
                .build();
        List<String> areaTagList = Arrays.asList(TestConst.TEST_AREA);
        SliceResponse<FuneralShopInfoResponse> mockPageResponse = SliceTestUtils.getMockSliceResponse(funeralShopInfoResponse);
        given(funeralShopReadUseCase.getFuneralShopList(any(), any())).willReturn(mockPageResponse);

        System.out.println("mockPageResponse");
        System.out.println(mockPageResponse);
        //when
        ResultActions result = mockMvc.perform(
                get("/funeral")
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("areaTagList", TestConst.TEST_AREA)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                requestParameters(
                                        parameterWithName("areaTagList").description("지역 태그 리스트")
                                ),
                                responseFields(
                                        fieldWithPath("content[].imgurl").description("가게 이미지 url"),
                                        fieldWithPath("content[].runtype").description("운영 타입. 24시간이면 FULL_TIME, 아니면 PART_TIME. PART_TIME으로 값이 오면 openTime, closingTime 활용"),
                                        fieldWithPath("content[].openTime").description("오픈 시간"),
                                        fieldWithPath("content[].closingTime").description("마감 시간"),
                                        fieldWithPath("content[].area").description("지역"),
                                        fieldWithPath("content[].shopName").description("가게 이름"),
                                        fieldWithPath("content[].address").description("주소"),
                                        fieldWithPath("content[].phoneNumber").description("핸드폰 번호"),
                                        fieldWithPath("content[].url").description("가게로 이동하는 url"),
                                        fieldWithPath("page").description("현재 페이지"),
                                        fieldWithPath("size").description("페이지 사이즈"),
                                        fieldWithPath("hasNext").description("다음 페이지 여부")
                                )
                        )
                );

    }
}
