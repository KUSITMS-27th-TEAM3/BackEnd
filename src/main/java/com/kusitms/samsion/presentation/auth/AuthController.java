package com.kusitms.samsion.presentation.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.application.auth.service.AuthReIssueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthReIssueService authReIssueService;

	@GetMapping("/reissue")
	public void reissue() {
		authReIssueService.reissue();
	}

}
