package com.kusitms.samsion.domain.grid.domain.entity;

import java.util.Objects;

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

import com.kusitms.samsion.common.domain.BaseEntity;
import com.kusitms.samsion.domain.user.domain.entity.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Grid extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grid_id")
	private Long id;

	private String gridImageUrl;
	private int gridCnt;

	@Enumerated(EnumType.STRING)
	private GridStatus gridStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Grid(String gridImageUrl, User user) {
		this.gridImageUrl = gridImageUrl;
		this.gridCnt = 0;
		this.gridStatus = GridStatus.GRID;
		this.user = user;
	}

	public void incGridCnt(){
		if(gridCnt<60&& Objects.equals(gridStatus, GridStatus.GRID)) {
			this.gridCnt++;
			updateGridStatus();
		}
	}

	private void updateGridStatus(){
		if(gridCnt == 60) {
			this.gridStatus = GridStatus.STAMP;
		}
	}
}
