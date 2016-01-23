package com.bee.team.app.board.entity;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.bee.team.all.Cell;
import com.bee.team.all.Laser;
import com.bee.team.all.Point;
import com.bee.team.base.BaseEntity;

public class Board extends BaseEntity implements Serializable {

	private String		boardId;
	private String		level;
	private String		params;
	private Laser		laser;
	private Cell[][]	cells;

	public Laser getLaser() {
		return laser;
	}

	public void setLaser(Laser laser) {
		this.laser = laser;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public Cell getCellFromPoint(Point p) {
		if(p.getX()>=cells.length || p.getX()<0) return null;
		if(p.getY()>=cells[0].length|| p.getY()<0) return null;
		return cells[p.getX()][p.getY()];
	}

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
		if (this == obj) { return true; }
		if (!(obj instanceof Board)) return false;
		return new EqualsBuilder().append(boardId, ((Board) obj).getBoardId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(boardId).append(level).toString();
	}
}
