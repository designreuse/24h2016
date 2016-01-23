package com.bee.team.all;

public class Cell {
	static String	CELL_EMPTY			= "empty";
	static String	CELL_WALL			= "wall";
	static String	CELL_MIROR			= "miror";
	static String	CELL_LASER_START	= "lstart";
	static String	CELL_LASER_END		= "lend";

	String			type;
	int				angle;

	public Cell(String type) {
		super();
		this.type = type;
	}

	public Cell(String type, int angle) {
		super();
		this.type = type;
		this.angle = angle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

}
