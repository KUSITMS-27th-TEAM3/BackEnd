package com.kusitms.samsion.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kusitms.samsion.common.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String nickname;
	private String email;

	@Embedded
	private MyPet mypet;


	@Builder
	public User(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
		this.mypet = MyPet.defaultValue();
	}

	public void updateMyPet(MyPet mypet){
		this.mypet.updateInfo(mypet);
	}



	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", nickname='" + nickname + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
