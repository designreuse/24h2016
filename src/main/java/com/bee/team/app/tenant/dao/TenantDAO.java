package com.bee.team.app.tenant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.bee.team.base.BaseDAO;
import com.bee.team.app.tenant.entity.Tenant;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class TenantDAO extends BaseDAO<Tenant> {
	private static final String	TABLE_NAME	= "tenant";
	private static final List<String>	TABLE_PKS	= Arrays.asList("tenant_id");
	List<String>				fieldList	= Arrays.asList("tenant_id","name","params","date_created","date_updated");

	public Tenant findTenantById(String tenantId) {
		return getOne("select " + fields() + " from tenant where tenant_id = ? ", new TenantMapper(), tenantId);
	}

	public List<Tenant> findAllTenant(String tenantId) {
		return getList("select " + fields() + " from tenant where tenant_id=?", new TenantMapper(), tenantId);
	}

	
	public void createTenant(Tenant tenant) {
		save(tenant);
	}

	public void updateTenant(String tenantId, Tenant tenant) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);
		f.remove("tenant_id");
		f.remove("date_created");
		tenant.setTenantId(tenantId);
		String sql = "update tenant set " + getUpdateCustomFields(f) + " where tenant_id = :tenantId ";
		update(sql, tenant);
	}

	public void deleteTenant(String tenantId) {
		delete("delete from tenant where tenant_id = ?", tenantId);
	}

	private final class TenantMapper implements RowMapper<Tenant> {
		public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tenant entity = new Tenant();
			initEntity(entity, rs);
			return entity;
		}
	}

	protected String getTableName() {
		return TABLE_NAME;
	}

	protected List<String> getTablePKs() {
		return TABLE_PKS;
	}

	protected List<String> getFieldList() {
		return fieldList;
	}
}
