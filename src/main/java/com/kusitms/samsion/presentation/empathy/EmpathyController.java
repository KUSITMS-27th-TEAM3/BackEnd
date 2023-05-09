package com.kusitms.samsion.presentation.empathy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.application.empathy.EmpathyToggleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmpathyController {

	private final EmpathyToggleService empathyToggleService;

	@GetMapping("/album/{albumId}/empathy")
	public void addEmpathy(@PathVariable Long albumId) {
		empathyToggleService.toggleEmpathy(albumId);
	}

}
