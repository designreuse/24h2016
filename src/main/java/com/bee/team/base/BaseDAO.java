package com.bee.team.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import com.bee.team.util.Utils;
import com.google.common.base.Joiner;
import com.gs.collections.impl.list.mutable.FastList;

public abstract class BaseDAO<T extends BaseEntity> {
	protected static final Logger			logger	= LoggerFactory.getLogger(BaseDAO.class);
	private boolean							logging	= false;

	protected JdbcTemplate					jdbcTemplate;

	protected NamedParameterJdbcTemplate	namedJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	protected T getOne(String sql, RowMapper<T> mapper, Object... params) {
		try {
			if (logger.isDebugEnabled() && logging) {
				logger.debug(fillSQLParameters(sql, params));
			}
			return jdbcTemplate.queryForObject(sql, params, mapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected T getOne2(String sql, RowMapper<T> mapper, Object... params) {
		if (logger.isDebugEnabled() && logging) {
			logger.debug(fillSQLParameters(sql, params));
		}
		return jdbcTemplate.queryForObject(sql, params, mapper);
	}

	protected Integer getInt(String sql, Object... params) {
		try {
			if (logger.isDebugEnabled() && logging) {
				logger.debug(fillSQLParameters(sql, params));
			}
			return jdbcTemplate.queryForObject(sql, Integer.class, params);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected Date getDate(String sql, Object... params) {
		try {
			if (logger.isDebugEnabled() && logging) {
				logger.debug(fillSQLParameters(sql, params));
			}
			return jdbcTemplate.queryForObject(sql, Date.class, params);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected Long getLong(String sql, Object... params) {
		try {
			if (logger.isDebugEnabled() && logging) {
				logger.debug(fillSQLParameters(sql, params));
			}
			return jdbcTemplate.queryForObject(sql, Long.class, params);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected String getString(String sql, Object... params) {
		try {
			if (logger.isDebugEnabled() && logging) {
				logger.debug(fillSQLParameters(sql, params));
			}
			return jdbcTemplate.queryForObject(sql, String.class, params);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected List<T> getList(String sql, RowMapper<T> mapper, Object... params) {
		if (logger.isDebugEnabled() && logging) {
			logger.debug(fillSQLParameters(sql, params));
		}
		return jdbcTemplate.query(sql, params, mapper);
	}

	protected List<List<Object>> getListObject(String sql, RowMapper<List<Object>> mapper, Object... params) {
		if (logger.isDebugEnabled() && logging) {
			logger.debug(fillSQLParameters(sql, params));
		}
		return jdbcTemplate.query(sql, params, mapper);
	}

	protected List<String> getListString(String sql, Object... params) {
		if (logger.isDebugEnabled() && logging) {
			logger.debug(fillSQLParameters(sql, params));
		}
		return jdbcTemplate.queryForList(sql, String.class, params);
	}

	protected List<Map<String, Object>> getMap(String sql, Object... params) {
		if (logger.isDebugEnabled() && logging) {
			logger.debug(fillSQLParameters(sql, params));
		}
		return jdbcTemplate.queryForList(sql, params);
	}

	protected void save(T entity) {
		if (getTablePKs().size() == 1) {
			// if only one PK, set autogenerated ID
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName(getTableName()).usingGeneratedKeyColumns(getTablePKs().get(0));
			Number newId = insert.executeAndReturnKey(getUnmapper(entity));
			if (logger.isDebugEnabled() && logging) {
				logger.debug(Utils.underscoreNameToCamelCase(getTableName()) + "DAO " + insert.getInsertString() + " " + getFieldValuesString(entity));
			}
			entity.setId(String.valueOf(newId.longValue()));
		} else {
			// if PK is composed, we don't have autogenerated pk
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName(getTableName());
			insert.execute(getUnmapper(entity));
			if (logger.isDebugEnabled() && logging) {
				logger.debug(Utils.underscoreNameToCamelCase(getTableName()) + "DAO " + insert.getInsertString() + " " + getFieldValuesString(entity));
			}
		}
	}

	protected void update(String sql, T entity) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(entity);
		if (logger.isDebugEnabled() && logging) {
			logger.debug(Utils.underscoreNameToCamelCase(getTableName()) + "DAO " + sql + " " + getFieldValuesString(entity));
		}
		namedJdbcTemplate.update(sql, params);
	}

	protected void update(String sql, Object... params) {
		if (logger.isDebugEnabled() && logging) {
			logger.debug(fillSQLParameters(sql, params));
		}
		this.jdbcTemplate.update(sql, params);
	}

	protected void delete(String sql, Object... params) {
		update(sql, params);
	}

	// Used for insert (save method)
	protected Map<String, Object> getUnmapper(T entity) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (getFieldList() != null) {
			FastList<String> fields = new FastList<String>(getFieldList());
			Date now = new Date();
			if (getFieldList().contains("date_created")) {
				fields.remove("date_created");
			}
			if (getFieldList().contains("date_updated")) {
				fields.remove("date_updated");
			}
//			if (getFieldList().contains("password")) {
//				fields.remove("password");
//			}
			for (String f : fields) {
				parameters.put(f, Utils.getFieldValue(entity, Utils.underscoreNameToCamelCase(f)));
			}
			if (getFieldList().contains("date_created")) {
				parameters.put("date_created", now);
			}
			if (getFieldList().contains("date_updated")) {
				parameters.put("date_updated", now);
			}
//			if (getFieldList().contains("password")) {
//				parameters.put("password", CryptoUtil.encrypt(((User) entity).getPassword()));
//			}
		}
		return parameters;
	}

	protected String fields() {
		return Joiner.on(',').join(getFieldList());
	}

	protected String fieldsWithPrefix(String prefix) {
		FastList<String> list = new FastList<String>(getFieldList());
		FastList<String> result = new FastList<String>(getFieldList().size());
		for (String string : list) {
			result.add(prefix + "." + string);
		}
		return Joiner.on(',').join(result);
	}

	protected String getUpdateSQL() {
		// By default we didn't update pks and tenant_id
		FastList<String> f = new FastList<String>(getFieldList());
		f.removeAll(getTablePKs());
		f.remove("tenant_id");
		return f.collect(field -> field + " = :" + Utils.underscoreNameToCamelCase(field)).makeString(", ");
	}

	protected String getUpdateCustomFields(FastList<String> fields) {
		return fields.collect(f -> f + " = :" + Utils.underscoreNameToCamelCase(f)).makeString(", ");
	}

	public void initEntity(T entity, ResultSet rs) throws SQLException {
		for (String f : getFieldList()) {
			String javaName = Utils.underscoreNameToCamelCase(f);
			String fieldType = Utils.getFieldType(entity, javaName);
			if (fieldType == null) {
				logger.error("One field type is null in one mapper : " + javaName + " in " + entity.getClass());
//			} else if (javaName.equals("password")) {
//				((User) entity).setPassword(CryptoUtil.decrypt(rs.getBytes(f)));
			} else if (fieldType.equals("java.lang.Long") || fieldType.equals("long")) {
				Utils.setFieldValue(entity, javaName, rs.getLong(f));
			} else if (fieldType.equals("java.lang.String")) {
				Utils.setFieldValue(entity, javaName, rs.getString(f));
			} else if (fieldType.equals("java.util.Date")) {
				Utils.setFieldValue(entity, javaName, rs.getTimestamp(f));
			} else if (fieldType.equals("java.lang.Boolean") || fieldType.equals("boolean")) {
				Utils.setFieldValue(entity, javaName, rs.getBoolean(f));
			} else if (fieldType.equals("java.lang.Integer")) {
				Utils.setFieldValue(entity, javaName, rs.getInt(f));
			} else if (fieldType.startsWith("java.util.HashMap")) {
				continue;
			} else {
				logger.error("One field type is not used in one mapper : " + fieldType + " in " + entity.getClass());
			}
		}
	}

	protected abstract String getTableName();

	protected abstract List<String> getTablePKs();

	protected abstract List<String> getFieldList();

	private String fillSQLParameters(String sql, Object[] sqlArgs) {
		DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");;
		DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		// initialize a StringBuilder with a guesstimated final length
		StringBuilder completedSqlBuilder = new StringBuilder(Math.round(sql.length() * 1.2f));
		completedSqlBuilder.append(Utils.underscoreNameToCamelCase(getTableName())).append("DAO ");
		int index, // will hold the index of the next ?
		prevIndex = 0; // will hold the index of the previous ? + 1

		// loop through each SQL argument
		for (Object arg : sqlArgs) {
			index = sql.indexOf("?", prevIndex);
			if (index == -1) break; // bail out if there's a mismatch in # of
									// args vs. ?'s

			// append the chunk of SQL coming before this ?
			completedSqlBuilder.append(sql.substring(prevIndex, index));
			if (arg == null)
				completedSqlBuilder.append("NULL");
			else if (arg instanceof String) {
				// wrap the String in quotes and escape any quotes within
				completedSqlBuilder.append('\'').append(arg.toString().replace("'", "''")).append('\'');
			} else if (arg instanceof Date) {
				// convert it to a Joda DateTime
				DateTime dateTime = new DateTime((Date) arg);
				// test to see if it's a DATE or a TIMESTAMP
				if (dateTime.getHourOfDay() == LocalTime.MIDNIGHT.getHourOfDay() && dateTime.getMinuteOfHour() == LocalTime.MIDNIGHT.getMinuteOfHour()
						&& dateTime.getSecondOfMinute() == LocalTime.MIDNIGHT.getSecondOfMinute()) {
					completedSqlBuilder.append("DATE '").append(DATE_FORMATTER.print(dateTime)).append('\'');
				} else {
					completedSqlBuilder.append("TIMESTAMP '").append(TIMESTAMP_FORMATTER.print(dateTime)).append('\'');
				}
			} else
				completedSqlBuilder.append(arg.toString());

			prevIndex = index + 1;
		}

		// add the rest of the SQL if any
		if (prevIndex != sql.length()) completedSqlBuilder.append(sql.substring(prevIndex));

		return completedSqlBuilder.toString();
	}

	private String getFieldValuesString(T user) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < getFieldList().size(); i++) {
			result.add(getFieldList().get(i) + "=" + Utils.getFieldValue(user, Utils.underscoreNameToCamelCase(getFieldList().get(i))));
		}
		return " params: " + Joiner.on(",").join(result);
	}
}
