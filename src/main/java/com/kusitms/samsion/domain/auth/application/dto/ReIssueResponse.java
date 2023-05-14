package com.kusitms.samsion.domain.auth.application.dto;

import lombok.Getter;

@Getter
public class ReIssueResponse {

	private final String accessToken;

	public ReIssueResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
