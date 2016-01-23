package com.bee.team.common.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.bee.team.base.BaseView;

@RequestScoped
@ManagedBean(name = "headerView")
public class HeaderView extends BaseView implements Serializable {

	@PostConstruct
	public void init() {
		WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
		if((session==null || getVisit()==null) && !getFacesContext().getPartialViewContext().isAjaxRequest()){
			throw new ViewExpiredException();
		}
	}
}
