package com.kusitms.samsion.application.auth.dto;

import lombok.Getter;

@Getter
public class ReIssueResponse {

	private String accessToken;

	public ReIssueResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
