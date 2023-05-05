package com.kusitms.samsion.presentation.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusitms.samsion.application.user.dto.request.MyPetUpdateRequest;
import com.kusitms.samsion.application.user.dto.response.MyPetResponse;
import com.kusitms.samsion.application.user.service.MyPetInfoService;
import com.kusitms.samsion.application.user.service.MyPetUpdateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final MyPetInfoService myPetInfoService;
	private final MyPetUpdateService myPetUpdateService;

	@GetMapping("/mypet")
	public MyPetResponse getMyPetInfo(){
		return myPetInfoService.getMyPetInfo();
	}

	@PostMapping("/mypet")
	public  MyPetResponse profileUpdate(@RequestBody MyPetUpdateRequest request){
		return myPetUpdateService.updateMyPetInfo(request);
	}

}
