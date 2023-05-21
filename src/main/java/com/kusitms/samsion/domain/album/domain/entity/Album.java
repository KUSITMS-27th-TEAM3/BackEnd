package com.kusitms.samsion.domain.album.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE album SET is_deleted = true WHERE album_id = ?")
@Where(clause = "is_deleted = false")
public class Album extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_id")
	private Long id;

	private String title;
	private String description;

	private boolean isDeleted = Boolean.FALSE;

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

	public void updateAlbum(String title, String description, Visibility visibility) {
		updateTitle(title);
		updateDescription(description);
		updateVisibility(visibility);
	}

	private void updateTitle(String title) {
		if(!Objects.equals(this.title, title)&& title != null) {
			this.title = title;
		}
	}

	private void updateDescription(String description) {
		if(!Objects.equals(this.description, description)&& description != null) {
			this.description = description;
		}
	}

	private void updateVisibility(Visibility visibility) {
		if(!Objects.equals(this.visibility, visibility)&& visibility != null) {
			this.visibility = visibility;
		}
	}

	public void addImage(AlbumImage albumImage){
		albumImages.add(albumImage);
	}

	public void addComment(Comment comment) {comments.add(comment);}


	public void addTag(Tag tag) {tags.add(tag);}

	public void changeAllImage(List<AlbumImage> albumImageList){
		this.albumImages = albumImageList;
	}

	public void changeAllTag(List<Tag> tagList){
		this.tags = tagList;
	}

	public void clearAllImage(){
		this.albumImages.clear();
		// this.albumImages= new ArrayList<>();
	}

	public void clearAllTag() {
		this.tags.clear();
		// this.tags = new ArrayList<>();
	}
}
