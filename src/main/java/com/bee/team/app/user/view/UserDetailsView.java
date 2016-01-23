package com.bee.team.app.user.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.user.entity.User;
import com.bee.team.app.user.service.UserService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "userDetailsView")
@URLMapping(id = "viewUserDetails", pattern = "/user/#{/\\\\d+/userId}", viewId = "/faces/user/userDetails.xhtml")
public class UserDetailsView extends BaseView implements Serializable {

	@Autowired
	private transient UserService	userService;

	private User					userTmp;

	@PostConstruct
	public void init() {
		initBean();
		userTmp = userService.findUserById(getUser(), getParam("userId"));
	}

	public User getUserTmp() {
		return userTmp;
	}

	public void setUserTmp(User userTmp) {
		this.userTmp = userTmp;
	}
}
