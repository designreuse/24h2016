package com.bee.team.app.user.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.user.entity.User;
import com.bee.team.app.user.service.UserService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "userListView")
@URLMapping(id = "viewUserList", pattern = "/user/list", viewId = "/faces/user/userList.xhtml")
public class UserListView extends BaseView implements Serializable {

	@Autowired
	private transient UserService	userService;

	private List<User>				userList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		userList = userService.findAllUser(getUser());
	}
	
	public String deleteUser() {
		userService.deleteUser(getUser(), getParam("userId"));
		refresh();
		return "";
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
