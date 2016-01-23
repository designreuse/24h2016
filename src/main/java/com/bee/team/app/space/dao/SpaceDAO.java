package com.bee.team.app.space.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.bee.team.base.BaseDAO;
import com.bee.team.app.space.entity.Space;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class SpaceDAO extends BaseDAO<Space> {
	private static final String	TABLE_NAME	= "space";
	private static final List<String>	TABLE_PKS	= Arrays.asList("space_id");
	List<String>				fieldList	= Arrays.asList("space_id","tenant_id","name","description","params","date_created","date_updated");

	public Space findSpaceById(String tenantId, String spaceId) {
		return getOne("select " + fields() + " from space where space_id = ? and tenant_id=?", new SpaceMapper(), spaceId, tenantId);
	}

	public List<Space> findAllSpace(String tenantId) {
		return getList("select " + fields() + " from space where tenant_id=?", new SpaceMapper(), tenantId);
	}

	
	public void createSpace(Space space) {
		save(space);
	}

	public void updateSpace(String tenantId, Space space) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);
		f.remove("tenant_id");
		f.remove("date_created");
		space.setTenantId(tenantId);
		String sql = "update space set " + getUpdateCustomFields(f) + " where space_id = :spaceId and tenant_id = :tenantId";
		update(sql, space);
	}

	public void deleteSpace(String tenantId, String spaceId) {
		delete("delete from space where space_id = ? and tenant_id = ?", spaceId, tenantId);
	}

	private final class SpaceMapper implements RowMapper<Space> {
		public Space mapRow(ResultSet rs, int rowNum) throws SQLException {
			Space entity = new Space();
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
