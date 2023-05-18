package com.kusitms.samsion.domain.album.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.kusitms.samsion.domain.comment.domain.entity.Comment;
import com.kusitms.samsion.domain.empathy.domain.entity.Empathy;
import com.kusitms.samsion.domain.user.domain.entity.User;

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

	private String title;
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

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Empathy> empathies = new ArrayList<>();

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Tag> tags = new ArrayList<>();

	@Builder
	public Album(String title, String description, Visibility visibility, User writer) {
		this.title = title;
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

	public void addTag(Tag tag) {tags.add(tag);}

}
