package com.bee.team.app.user.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.user.entity.User;
import com.bee.team.app.user.service.UserService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@RequestScoped
@ManagedBean(name = "userCreateView")
@URLMapping(id = "viewUserCreate", pattern = "/user/create", viewId = "/faces/user/userCreate.xhtml")
public class UserCreateView extends BaseView implements Serializable {

	@Autowired
	private transient UserService	userService;

	private User					userTmp;

	@PostConstruct
	public void init() {
		initBean();
		userTmp = new User();
	}

	public String createUser() {
		userTmp.setTenantId(getTenantId());
		userService.createUser(getUser(), userTmp);
		return "pretty:viewUserList";
	}

	public User getUserTmp() {
		return userTmp;
	}

	public void setUserTmp(User userTmp) {
		this.userTmp = userTmp;
	}

}
