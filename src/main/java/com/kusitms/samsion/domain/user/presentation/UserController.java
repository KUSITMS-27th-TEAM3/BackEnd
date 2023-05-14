package com.kusitms.samsion.domain.user.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.domain.user.application.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.domain.user.application.dto.response.MyPetResponse;
import com.kusitms.samsion.domain.user.application.service.MyPetInfoUseCase;
import com.kusitms.samsion.domain.user.application.service.MyPetUpdateUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final MyPetInfoUseCase myPetInfoUseCase;
	private final MyPetUpdateUseCase myPetUpdateUseCase;

	@GetMapping("/mypet")
	public MyPetResponse getMyPetInfo(){
		return myPetInfoUseCase.getMyPetInfo();
	}

	@PostMapping(value = "/mypet")
	public  MyPetResponse profileUpdate(@ModelAttribute MyPetUpdateRequest request){
		return myPetUpdateUseCase.updateMyPetInfo(request);
	}

}
