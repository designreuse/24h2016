package com.bee.team.app.space.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bee.team.base.BaseService;
import com.bee.team.app.space.dao.SpaceDAO;
import com.bee.team.app.space.entity.Space;
import com.bee.team.app.user.entity.User;

@Service("spaceService")
public class SpaceService extends BaseService {

	@Autowired
	SpaceDAO	spaceDAO;
	
	public Space findSpaceById(User currentUser, String spaceId) {
		return spaceDAO.findSpaceById(currentUser.getTenantId(), spaceId);
	}

	public List<Space> findAllSpace(User currentUser) {
		return spaceDAO.findAllSpace(currentUser.getTenantId());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createSpace(User currentUser, Space space) {
		spaceDAO.createSpace(space);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateSpace(User currentUser, Space space) {
		spaceDAO.updateSpace(currentUser.getTenantId(), space);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSpace(User currentUser, String spaceId) {
		spaceDAO.deleteSpace(currentUser.getTenantId(), spaceId);
	}
	
	
}
