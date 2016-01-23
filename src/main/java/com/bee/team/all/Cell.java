package com.bee.team.all;

public class Cell {
	
	public static final String CELL_EMPTY       = "empty";
	public static final String CELL_WALL        = "wall";
	public static final String CELL_MIRROR       = "mirror";
	public static final String CELL_LASER_START = "lstart";
	public static final String CELL_LASER_END   = "lend";

	public static final int UNDEFINED = -1;
	public static final int N = 0;
	public static final int E = 1;
	public static final int S = 2;
	public static final int W = 3;

	private String type = CELL_EMPTY;
	private int angle = UNDEFINED;
	private int laserOrigin = UNDEFINED;

	
	public Cell(String type) {
		super();
		this.type = type;
	}

	public Cell(String type, int angle) {
		super();
		this.type = type;
		this.angle = angle;
	}

	public Cell(String type, int angle, int laserOrigin) {
		super();
		this.type = type;
		this.angle = angle;
		this.laserOrigin = laserOrigin;
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

	public int getLaserOrigin() {
		return laserOrigin;
	}

	public void setLaserOrigin(int laserOrigin) {
		this.laserOrigin = laserOrigin;
	}

}
