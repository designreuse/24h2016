package com.bee.team.app.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.bee.team.base.BaseEntity;

public class Tenant extends BaseEntity implements Serializable {
	
	private String tenantId;
	private String name;
	private String params;
	private Date dateCreated;
	private Date dateUpdated;

	public String getId() {
		return tenantId;
	}

	public void setId(String id) {
		this.tenantId = id;
	}
	
	public String getTenantId() {
		return tenantId;
	}
		
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public String getName() {
		return name;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParams() {
		return params;
	}
		
	public void setParams(String params) {
		this.params = params;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
		
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getDateUpdated() {
		return dateUpdated;
	}
		
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tenantId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tenant))
			return false;
		return new EqualsBuilder().append(tenantId, ((Tenant) obj).getTenantId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(tenantId).append(name).toString();
	}
}