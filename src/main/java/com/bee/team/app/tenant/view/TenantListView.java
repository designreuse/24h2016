package com.bee.team.app.tenant.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.tenant.entity.Tenant;
import com.bee.team.app.tenant.service.TenantService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "tenantListView")
@URLMapping(id = "viewTenantList", pattern = "/tenant/list", viewId = "/faces/tenant/tenantList.xhtml")
public class TenantListView extends BaseView implements Serializable {

	@Autowired
	private transient TenantService	tenantService;

	private List<Tenant>				tenantList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		tenantList = tenantService.findAllTenant(getUser());
	}
	
	public String deleteTenant() {
		tenantService.deleteTenant(getUser(), getParam("tenantId"));
		refresh();
		return "";
	}
	
	public List<Tenant> getTenantList() {
		return tenantList;
	}

	public void setTenantList(List<Tenant> tenantList) {
		this.tenantList = tenantList;
	}
}
