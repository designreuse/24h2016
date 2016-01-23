package com.bee.team.all;

public class Point {
	
	private int row;
	private int column;
	
	public Point() {
	}
	
	public Point(int x, int y) {
		this.row = x;
		this.column = y;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String toString() {
		return "["+row+","+column+"]";
	}
}
