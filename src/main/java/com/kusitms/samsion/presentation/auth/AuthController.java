package com.kusitms.samsion.presentation.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.application.auth.dto.ReIssueResponse;
import com.kusitms.samsion.application.auth.service.AuthReIssueService;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.util.HeaderUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthReIssueService authReIssueService;

	@GetMapping("/reissue")
	public ReIssueResponse reissue() {
		final String refreshTokenHeader = HeaderUtils.getHeader(ApplicationConst.REFRESH_TOKEN_HEADER);
		return authReIssueService.reissue(refreshTokenHeader);
	}

}
