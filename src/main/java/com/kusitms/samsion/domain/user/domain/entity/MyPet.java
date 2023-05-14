package com.kusitms.samsion.domain.user.domain.entity;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPet {

	String petName;
	String petImageUrl;
	String description;

	@Builder
	public MyPet(String petName, String petImageUrl, String description) {
		this.petName = petName;
		this.petImageUrl = petImageUrl;
		this.description = description;
	}

	public void updateInfo(MyPet myPet){
		this.petName = myPet.getPetName();
		this.petImageUrl = myPet.getPetImageUrl();
		this.description = myPet.getDescription();
	}

	public static MyPet defaultValue(){
		return MyPet.builder()
				.petName("익명이")
				.petImageUrl("")
				.description("익명이 설명이에요!")
				.build();
	}


}
