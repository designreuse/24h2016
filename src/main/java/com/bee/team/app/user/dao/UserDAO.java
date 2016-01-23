package com.bee.team.app.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.bee.team.base.BaseDAO;
import com.bee.team.app.user.entity.User;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class UserDAO extends BaseDAO<User> {
	private static final String	TABLE_NAME	= "user";
	private static final List<String>	TABLE_PKS	= Arrays.asList("user_id");
	List<String>				fieldList	= Arrays.asList("user_id","first_name","last_name","login","password","tenant_id","params","date_created","date_updated","date_deleted","date_disable");

	public User findUserById(String tenantId, String userId) {
		return getOne("select " + fields() + " from user where user_id = ? and tenant_id=?", new UserMapper(), userId, tenantId);
	}

	public List<User> findAllUser(String tenantId) {
		return getList("select " + fields() + " from user where tenant_id=?", new UserMapper(), tenantId);
	}

	
	public User findUserDeletedById(String tenantId, String id) {
		return getOne("select " + fields() + " from user where user_id=? and tenant_id=? and date_deleted is not null",
				new UserMapper(), id, tenantId);
	}

	public User findUserDisabledById(String tenantId, String id) {
		return getOne("select " + fields() + " from user where user_id=? and tenant_id=? and date_disable is not null",
				new UserMapper(), id, tenantId);
	}

	public User findUserByLogin(String login) {
		return getOne(
				"select " + fieldsWithPrefix("u")
						+ " from user u where login=? and tenant_id <>0  and date_deleted is null",
				new UserMapper(), login);
	}

	public User findUserNotDisableByLogin(String login) {
		return getOne(
				"select " + fieldsWithPrefix("u")
						+ " from user u where login=? and tenant_id <>0  and date_deleted is null and date_disable is null",
				new UserMapper(), login);
	}

	public User findUserByLogin(String tenantId, String login) {
		return getOne(
				"select " + fieldsWithPrefix("u")
						+ " from user u where tenant_id=? and login=?  and date_deleted is null",
				new UserMapper(), tenantId, login);
	}

	public List<User> findAllUserDisable(String tenantId) {
		return getList("select " + fields() + " from user where tenant_id=? and date_disable is not null",
				new UserMapper(), tenantId);
	}

	public void disableUser(String tenantId, String userId) {
		String sql = "update user set date_disable=? where user_id = ? and tenant_id = ?";
		update(sql, new Date(), userId, tenantId);
	}

	public void enableUser(String tenantId, String userId) {
		String sql = "update user set date_disable=? where user_id = ? and tenant_id = ?";
		update(sql, null, userId, tenantId);
	}

	public void updateUserPassword(String tenant, String id, String password) {
		update("update user set password = ? where user_id = ? and tenant_id=?", password, id,
				tenant);
	}
	
	public void createUser(User user) {
		save(user);
	}

	public void updateUser(String tenantId, User user) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);
		f.remove("tenant_id");
		f.remove("date_created");
		user.setTenantId(tenantId);
		String sql = "update user set " + getUpdateCustomFields(f) + " where user_id = :userId and tenant_id = :tenantId";
		update(sql, user);
	}

	public void deleteUser(String tenantId, String userId) {
		delete("delete from user where user_id = ? and tenant_id = ?", userId, tenantId);
	}

	private final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User entity = new User();
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
