package com.bee.team.app.user.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.bee.team.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ViewScoped
@ManagedBean(name = "userDashboardView")
@URLMappings(mappings = {
		@URLMapping(id = "viewUserDashboard", pattern = "/home", viewId = "/faces/user/userDashboard.xhtml") })
public class UserDashboardView extends BaseView {

	@PostConstruct
	public void init() {
		initBean();
	}

	@URLAction(mappingId = "viewUserDashboard", onPostback = false)
	public void viewUserDashboardOnLoad() {
	}
}
