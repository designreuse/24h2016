package com.bee.team.app.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bee.team.base.BaseService;
import com.bee.team.app.user.dao.UserDAO;
import com.bee.team.app.user.entity.User;


@Service("userService")
public class UserService extends BaseService {

	@Autowired
	UserDAO	userDAO;
	
	public User findUserById(User currentUser, String userId) {
		return userDAO.findUserById(currentUser.getTenantId(), userId);
	}

	public List<User> findAllUser(User currentUser) {
		return userDAO.findAllUser(currentUser.getTenantId());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createUser(User currentUser, User user) {
		userDAO.createUser(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUser(User currentUser, User user) {
		userDAO.updateUser(currentUser.getTenantId(), user);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteUser(User currentUser, String userId) {
		userDAO.deleteUser(currentUser.getTenantId(), userId);
	}
	
	
	public User findUserDeletedById(User user, String userId) {
		return userDAO.findUserDeletedById(user.getTenantId(), userId);
	}
	
	public User findUserDisabledById(User user, String userId) {
		return userDAO.findUserDisabledById(user.getTenantId(), userId);
	}

	public User findUserByLogin(String login) {
		return userDAO.findUserByLogin(login);
	}

	public User findUserByLogin(String tenantId, String login) {
		return userDAO.findUserByLogin(tenantId, login);
	}

	public User findUserNotDisableByLogin(String login) {
		return userDAO.findUserNotDisableByLogin(login);
	}

	public List<User> findAllUserDisable(User user) {
		return userDAO.findAllUserDisable(user.getTenantId());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUserPassword(String tenantId, String id, String password) {
		userDAO.updateUserPassword(tenantId, id, password);
	}
	
}
