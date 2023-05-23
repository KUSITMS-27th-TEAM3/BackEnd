package com.kusitms.samsion.domain.empathy.application.dto.response;

import lombok.Getter;

@Getter
public class EmpathyCountResponse {

    private final long empathyCount;
    private final boolean empathyExistAboutUser;

    public EmpathyCountResponse(long empathyCount, boolean empathyExistAboutUser) {
        this.empathyCount = empathyCount;
        this.empathyExistAboutUser = empathyExistAboutUser;
    }
}
