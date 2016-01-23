package com.bee.team.app.space.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.space.entity.Space;
import com.bee.team.app.space.service.SpaceService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@RequestScoped
@ManagedBean(name = "spaceCreateView")
@URLMapping(id = "viewSpaceCreate", pattern = "/space/create", viewId = "/faces/space/spaceCreate.xhtml")
public class SpaceCreateView extends BaseView implements Serializable {

	@Autowired
	private transient SpaceService	spaceService;

	private Space					space;

	@PostConstruct
	public void init() {
		initBean();
		space = new Space();
	}

	public String createSpace() {
		space.setTenantId(getTenantId());
		spaceService.createSpace(getUser(), space);
		return "pretty:viewSpaceList";
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

}
