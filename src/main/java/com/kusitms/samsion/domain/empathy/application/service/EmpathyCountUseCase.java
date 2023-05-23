package com.kusitms.samsion.domain.empathy.application.service;

import com.kusitms.samsion.common.annotation.UseCase;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.empathy.application.dto.response.EmpathyCountResponse;
import com.kusitms.samsion.domain.empathy.application.mapper.EmpathyMapper;
import com.kusitms.samsion.domain.empathy.domain.service.EmpathyQueryService;
import com.kusitms.samsion.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class EmpathyCountUseCase {

    private final UserUtils userUtils;
    private final EmpathyQueryService empathyQueryService;

    public EmpathyCountResponse getEmpathyCount(final Long albumId) {
        final User user = userUtils.getUser();
        final long empathyCount = empathyQueryService.getEmpathyCountByAlbumId(albumId);
        boolean existEmpathyByUserId = empathyQueryService.isEmpathyByUserIdAndAlbumId(user.getId(), albumId);
        return EmpathyMapper.mapToEmpathyCountResponse(empathyCount, existEmpathyByUserId);
    }
}
