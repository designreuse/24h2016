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
@ManagedBean(name = "spaceUpdateView")
@URLMapping(id = "viewSpaceUpdate", pattern = "/space/update/#{/\\\\d+/spaceId}", viewId = "/faces/space/spaceUpdate.xhtml")
public class SpaceUpdateView extends BaseView implements Serializable {

	@Autowired
	private transient SpaceService	spaceService;
	
	private Space					space;

	@PostConstruct
	public void init() {
		initBean();
		space = spaceService.findSpaceById(getUser(), getParam("spaceId"));
	}

	public String updateSpace() {
		spaceService.updateSpace(getUser(), space);
		return "pretty:viewSpaceList";
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}
}
