package com.bee.team.app.help.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.bee.team.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@RequestScoped
@ManagedBean(name = "helpView")
@URLMapping(id = "viewHelp", pattern = "/application/help", viewId = "/faces/help/help.xhtml")
public class HelpView extends BaseView implements Serializable {

	@PostConstruct
	public void init() {
		initBean();
	}

}