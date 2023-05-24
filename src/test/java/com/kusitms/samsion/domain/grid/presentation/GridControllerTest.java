package com.kusitms.samsion.domain.grid.presentation;

import com.kusitms.samsion.common.config.CommonRestDocs;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.consts.TestConst;
import com.kusitms.samsion.common.slice.ListResponse;
import com.kusitms.samsion.domain.grid.application.dto.GridCheck;
import com.kusitms.samsion.domain.grid.application.dto.response.GridInfoResponse;
import com.kusitms.samsion.domain.grid.application.dto.response.StampResponse;
import com.kusitms.samsion.domain.grid.application.service.GridGetUseCase;
import com.kusitms.samsion.domain.grid.application.service.StampGetUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GridController.class)
@DisplayName("GridController 테스트")
public class GridControllerTest extends CommonRestDocs {

    @MockBean
    GridGetUseCase gridGetUseCase;

    @MockBean
    StampGetUseCase stampGetUseCase;

    @Test
    void 그리드_조회() throws Exception {
        List<GridCheck> gridCheckList = new ArrayList<>(Arrays.asList(new GridCheck(true, 1),
                new GridCheck(true, 2), new GridCheck(true, 3), new GridCheck(true, 4)));
        GridInfoResponse gridInfoResponse = new GridInfoResponse(gridCheckList, TestConst.TEST_GRID_IMG_URL);
        given(gridGetUseCase.getGrid()).willReturn(gridInfoResponse);

        //when
        ResultActions result = mockMvc.perform(
                get("/grid")
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                responseFields(
                                        fieldWithPath("gridCheckList[].check").description("그리드 체크 여부"),
                                        fieldWithPath("gridCheckList[].gridNum").description("그리드 번호"),
                                        fieldWithPath("gridImageUrl").description("그리드 이미지 링크")
                                )
                        )
                );
    }

    @Test
    void 스탬프_조회() throws Exception {
        List<StampResponse> stampResponseList = new ArrayList<>(Arrays.asList(new StampResponse(TestConst.TEST_GRID_ID, TestConst.TEST_GRID_IMG_URL)));
        ListResponse<StampResponse> stampResponseListResponse = ListResponse.of(stampResponseList);
        given(stampGetUseCase.getStampList()).willReturn(stampResponseListResponse);

        //when
        ResultActions result = mockMvc.perform(
                get("/grid/stamp")
                        .header(ApplicationConst.ACCESS_TOKEN_HEADER, "access token")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("access token")
                                ),
                                responseFields(
                                        fieldWithPath("content[].stampId").description("스탬프 아이디"),
                                        fieldWithPath("content[].imageUrl").description("스탬프 이미지 링크")
                                )
                        )
                );
    }
}
