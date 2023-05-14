package com.kusitms.samsion.domain.comment.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.kusitms.samsion.common.domain.BaseEntity;
import com.kusitms.samsion.domain.album.domain.entity.Album;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    // 부모 정의 (셀프 참조)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "super_comment_id")
    private Comment parent;

    //자식 정의 (대댓글)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> childList = new ArrayList<>();

    public void addChild(Comment child){
        childList.add(child);
    }

    //== 수정 메서드 ==//
    public void updateDescription(String description) {
        this.description = description;
    }

    @Builder
    public Comment(String description, Album album, User writer, Comment parent) {
        this.description = description;
        this.album = album;
        this.writer = writer;
        this.parent = parent;
        album.addComment(this);
        if(parent != null)
            parent.addChild(this);
    }


}
