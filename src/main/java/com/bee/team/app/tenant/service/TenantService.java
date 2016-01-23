package com.bee.team.app.tenant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bee.team.base.BaseService;
import com.bee.team.app.tenant.dao.TenantDAO;
import com.bee.team.app.tenant.entity.Tenant;
import com.bee.team.app.user.entity.User;

@Service("tenantService")
public class TenantService extends BaseService {

	@Autowired
	TenantDAO	tenantDAO;
	
	public Tenant findTenantById(User currentUser, String tenantId) {
		return tenantDAO.findTenantById(tenantId);
	}

	public List<Tenant> findAllTenant(User currentUser) {
		return tenantDAO.findAllTenant(currentUser.getTenantId());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createTenant(User currentUser, Tenant tenant) {
		tenantDAO.createTenant(tenant);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateTenant(User currentUser, Tenant tenant) {
		tenantDAO.updateTenant(currentUser.getTenantId(), tenant);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTenant(User currentUser, String tenantId) {
		tenantDAO.deleteTenant(tenantId);
	}
	
	
}
