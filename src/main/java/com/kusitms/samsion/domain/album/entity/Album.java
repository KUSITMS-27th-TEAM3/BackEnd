package com.kusitms.samsion.domain.album.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.kusitms.samsion.common.domain.BaseEntity;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Album extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_id")
	private Long id;

	private String description;

	@Enumerated(EnumType.STRING)
	private Visibility visibility;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User writer;

	@OneToMany(mappedBy = "album")
	private List<AlbumImage> albumImages = new ArrayList<>();

	@Builder
	public Album(String description, Visibility visibility, User writer) {
		this.description = description;
		this.visibility = visibility;
		this.writer = writer;
	}

	public void addImage(AlbumImage albumImage){
		albumImages.add(albumImage);
	}

}
