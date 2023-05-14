package com.kusitms.samsion.domain.empathy.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.domain.empathy.application.service.EmpathyToggleUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmpathyController {

	private final EmpathyToggleUseCase empathyToggleUseCase;

	@GetMapping("/album/{albumId}/empathy")
	public void addEmpathy(@PathVariable Long albumId) {
		empathyToggleUseCase.toggleEmpathy(albumId);
	}

}