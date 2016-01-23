package com.bee.team.app.tenant.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.tenant.entity.Tenant;
import com.bee.team.app.tenant.service.TenantService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@RequestScoped
@ManagedBean(name = "tenantCreateView")
@URLMapping(id = "viewTenantCreate", pattern = "/tenant/create", viewId = "/faces/tenant/tenantCreate.xhtml")
public class TenantCreateView extends BaseView implements Serializable {

	@Autowired
	private transient TenantService	tenantService;

	private Tenant					tenant;

	@PostConstruct
	public void init() {
		initBean();
		tenant = new Tenant();
	}

	public String createTenant() {
		tenant.setTenantId(getTenantId());
		tenantService.createTenant(getUser(), tenant);
		return "pretty:viewTenantList";
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
