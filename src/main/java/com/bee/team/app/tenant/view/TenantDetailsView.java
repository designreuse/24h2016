package com.bee.team.app.tenant.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.tenant.entity.Tenant;
import com.bee.team.app.tenant.service.TenantService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "tenantDetailsView")
@URLMapping(id = "viewTenantDetails", pattern = "/tenant/#{/\\\\d+/tenantId}", viewId = "/faces/tenant/tenantDetails.xhtml")
public class TenantDetailsView extends BaseView implements Serializable {

	@Autowired
	private transient TenantService	tenantService;

	private Tenant					tenant;

	@PostConstruct
	public void init() {
		initBean();
		tenant = tenantService.findTenantById(getUser(), getParam("tenantId"));
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
}
