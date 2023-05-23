package com.kusitms.samsion.domain.album.domain.entity;

import com.kusitms.samsion.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class AlbumImage extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_image_id")
	private Long id;

	private String imageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id")
	private Album album;

	public AlbumImage(String imageUrl, Album album) {
		this.imageUrl = imageUrl;
		this.album = album;
		album.addImage(this);
	}
}
