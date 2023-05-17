package com.kusitms.samsion.domain.grid.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@RedisHash(value = "gridCountTracker", timeToLive = 60 * 60 * 24)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GridCountTracker implements Serializable {

	@Id
	private Long id;
	@Indexed
	private Long gridId;
	private LocalDateTime createdDate;

	public GridCountTracker(Long gridId, LocalDateTime createdDate) {
		this.gridId = gridId;
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "GridCountTracker{" +
			"id=" + id +
			", gridId=" + gridId +
			", createdDate=" + createdDate +
			'}';
	}
}
