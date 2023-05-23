package com.kusitms.samsion.domain.empathy.application.mapper;

import com.kusitms.samsion.domain.empathy.application.dto.response.EmpathyCountResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmpathyMapper {

    public static EmpathyCountResponse mapToEmpathyCountResponse(final long empathyCount, final boolean empathyExistAboutUser) {
        return new EmpathyCountResponse(empathyCount, empathyExistAboutUser);
    }
}
