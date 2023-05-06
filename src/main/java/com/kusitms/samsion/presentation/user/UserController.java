package com.kusitms.samsion.presentation.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping(value = "/mypet",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public  MyPetResponse profileUpdate(@RequestPart(value = "file", required = false) MultipartFile multipartFile, @RequestPart(value = "request") MyPetUpdateRequest request){
		return myPetUpdateService.updateMyPetInfo(multipartFile, request);
	}

}
