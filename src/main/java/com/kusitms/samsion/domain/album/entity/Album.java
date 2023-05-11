package com.kusitms.samsion.domain.album.entity;

import com.kusitms.samsion.common.domain.BaseEntity;
import com.kusitms.samsion.domain.comment.entity.Comment;
import com.kusitms.samsion.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<AlbumImage> albumImages = new ArrayList<>();

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@Builder
	public Album(String description, Visibility visibility, User writer) {
		this.description = description;
		this.visibility = visibility;
		this.writer = writer;
	}

	public void addImage(AlbumImage albumImage){
		albumImages.add(albumImage);
	}

	public void addComment(Comment comment) {comments.add(comment);}

	public void addImageList(List<AlbumImage> albumImageList){
		albumImages.addAll(albumImageList);
	}

}
