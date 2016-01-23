package com.bee.team.app.board.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.bee.team.base.BaseEntity;

public class Board extends BaseEntity implements Serializable {
	
	private String boardId;
	private String level;
	private String params;

	public String getId() {
		return boardId;
	}

	public void setId(String id) {
		this.boardId = id;
	}
	
	public String getBoardId() {
		return boardId;
	}
		
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	public String getLevel() {
		return level;
	}
		
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getParams() {
		return params;
	}
		
	public void setParams(String params) {
		this.params = params;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(boardId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Board))
			return false;
		return new EqualsBuilder().append(boardId, ((Board) obj).getBoardId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(boardId).append(level).toString();
	}
}