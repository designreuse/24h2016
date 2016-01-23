package com.bee.team.app.space.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.bee.team.base.BaseEntity;

public class Space extends BaseEntity implements Serializable {
	
	private String spaceId;
	private String tenantId;
	private String name;
	private String description;
	private String params;
	private Date dateCreated;
	private Date dateUpdated;

	public String getId() {
		return spaceId;
	}

	public void setId(String id) {
		this.spaceId = id;
	}
	
	public String getSpaceId() {
		return spaceId;
	}
		
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
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
	
	public String getDescription() {
		return description;
	}
		
	public void setDescription(String description) {
		this.description = description;
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
		return new HashCodeBuilder().append(spaceId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Space))
			return false;
		return new EqualsBuilder().append(spaceId, ((Space) obj).getSpaceId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(spaceId).append(name).toString();
	}
}