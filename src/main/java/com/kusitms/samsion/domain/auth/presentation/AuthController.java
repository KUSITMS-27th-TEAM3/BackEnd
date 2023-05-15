package com.kusitms.samsion.domain.auth.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.domain.auth.application.dto.ReIssueResponse;
import com.kusitms.samsion.domain.auth.application.service.AuthReIssueUseCase;
import com.kusitms.samsion.common.consts.ApplicationConst;
import com.kusitms.samsion.common.util.HeaderUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthReIssueUseCase authReIssueUseCase;

	@GetMapping("/reissue")
	public ReIssueResponse reissue() {
		final String refreshTokenHeader = HeaderUtils.getHeader(ApplicationConst.REFRESH_TOKEN_HEADER);
		return authReIssueUseCase.reissue(refreshTokenHeader);
	}

	@GetMapping("/success")
	public String success() {
		return "success";
	}

}
