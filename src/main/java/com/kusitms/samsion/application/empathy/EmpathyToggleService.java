package com.kusitms.samsion.application.empathy;

import org.springframework.transaction.annotation.Transactional;

import com.kusitms.samsion.common.annotation.ApplicationService;
import com.kusitms.samsion.common.util.UserUtils;
import com.kusitms.samsion.domain.album.entity.Album;
import com.kusitms.samsion.domain.album.service.AlbumQueryService;
import com.kusitms.samsion.domain.empathy.service.EmpathyDeleteService;
import com.kusitms.samsion.domain.empathy.service.EmpathyQueryService;
import com.kusitms.samsion.domain.empathy.service.EmpathySaveService;
import com.kusitms.samsion.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Transactional
@ApplicationService
@RequiredArgsConstructor
public class EmpathyToggleService {
	
	private final UserUtils userUtils;
	private final EmpathyQueryService empathyQueryService;
	private final EmpathySaveService empathySaveService;
	private final EmpathyDeleteService empathyDeleteService;
	private final AlbumQueryService albumQueryService;

	/**
	 * 공감버튼의 경우 공감을 누르면 공감이 취소되고, 공감이 취소되어있으면 공감이 된다.
	 * 공감 취소의 경우 논리적 취소를 할지 아니면 물리적 삭제를 통한 취소를할지 결정해야함
	 */
	public void toggleEmpathy(Long albumId) {
		final User user = userUtils.getUser();
		if(empathyQueryService.isEmpathyByUserIdAndAlbumId(user.getId(), albumId)) {
			empathyDeleteService.deleteEmpathy(user.getId(), albumId);
		}else{
			final Album album = albumQueryService.getAlbumById(albumId);
			empathySaveService.saveEmpathy(user, album);
		}
	}
}
