package com.bee.team.app.space.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.space.entity.Space;
import com.bee.team.app.space.service.SpaceService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "spaceListView")
@URLMapping(id = "viewSpaceList", pattern = "/space/list", viewId = "/faces/space/spaceList.xhtml")
public class SpaceListView extends BaseView implements Serializable {

	@Autowired
	private transient SpaceService	spaceService;

	private List<Space>				spaceList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		spaceList = spaceService.findAllSpace(getUser());
	}
	
	public String deleteSpace() {
		spaceService.deleteSpace(getUser(), getParam("spaceId"));
		refresh();
		return "";
	}
	
	public List<Space> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}
}
