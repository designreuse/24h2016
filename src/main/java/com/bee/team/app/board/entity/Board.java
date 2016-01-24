package com.bee.team.app.board.entity;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.bee.team.all.Cell;
import com.bee.team.all.Laser;
import com.bee.team.all.Point;
import com.bee.team.base.BaseEntity;

public class Board extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String boardId;
	private String params;

	private String levelNumber;
	private String levelName;
	private String width;
	private String height;

	private Laser laser;
	private Cell[][] cells;
	private List<Cell> pioche;

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
		if (p.getRow() >= cells.length || p.getRow() < 0)
			return null;
		if (p.getColumn() >= cells[0].length || p.getColumn() < 0)
			return null;
		return cells[p.getRow()][p.getColumn()];
	}
	
	public Point getPointFromCell(Cell c) {
		for(int i=0;i<cells.length;i++)
		for(int j=0;j<cells[0].length;j++) {
			if(cells[i][j]==c) return new Point(i,j);
		}
		return null;
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

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(String levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void resetCells(final int height, final int width) {
		cells = new Cell[height][width];
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				cells[row][column] = new Cell(Cell.CELL_EMPTY);
			}
		}
	}

	public void resetLaser() {
		for (Cell[] line : cells)
			for (Cell cell : line) {
				cell.resetLaser();
			}
		laser.getPath().clear();
	}
	
	public boolean checkPointsReached() {
		for (Cell[] line : cells)
			for (Cell cell : line) {
				if(cell.isCheckpoint() && !cell.hasLaser()) return false;
			}
		return true;
	}
	
	public Cell findOtherGate(Cell gate) {
		for (Cell[] line : cells)
			for (Cell cell : line) {
				if(cell.isGate() && cell!=gate) return cell;
			}
		return null;
	}

	public List<Cell> getPioche() {
		return pioche;
	}

	public void setPioche(List<Cell> pioche) {
		this.pioche = pioche;
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
		return new ToStringBuilder(this).append(boardId).append(levelNumber)
				.append(levelName).append(width).append(height).toString();
	}

}
