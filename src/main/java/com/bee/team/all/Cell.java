package com.bee.team.all;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String CELL_EMPTY       = "empty";
	public static final String CELL_WALL        = "wall";
	public static final String CELL_MIRROR       = "mirror";
	public static final String CELL_CHECKPOINT   = "checkpoint";
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
	
	private boolean fixed = true;
	
	
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

	public boolean isEmpty() {
		return type.equals(CELL_EMPTY);
	}

	public boolean isMirror() {
		return type.equals(CELL_MIRROR);
	}

	public boolean isWall() {
		return type.equals(CELL_WALL);
	}

	public boolean isCheckpoint() {
		return type.equals(CELL_CHECKPOINT);
	}
	
	public boolean isLaserStart() {
		return type.equals(CELL_LASER_START);
	}
	
	public boolean isLaserEnd() {
		return type.equals(CELL_LASER_END);
	}
	
	public boolean hasLaser() {
		return laserOrigin!=UNDEFINED;
	}
	
	
	
	public String getImage() {
		if (type.equals(CELL_WALL)) {
			return "mur";
		}
		if (type.equals(CELL_EMPTY)) {
			if (laserOrigin == UNDEFINED) {
					return null;
			} 
			if (laserOrigin == N || laserOrigin == S) {
				return "laser_v";
			}
			return "laser_h";
		}
		if (type.equals(CELL_LASER_START)) {
			return "start";
		}
		if (type.equals(CELL_LASER_END)) {
			if (laserOrigin != UNDEFINED) {
				return "end";
			}
			return "end_point";
		}
		if (type.equals(CELL_MIRROR)) {
			if (laserOrigin != UNDEFINED) {
				return "mirroir_laser";
			}
			return "mirroir";
		}
		if (type.equals(CELL_CHECKPOINT)) {
			if (hasLaser()) {
				return "checkpoint_actif";
			}
			return "checkpoint";
		}
		
		return null;
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
	
	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed){
		this.fixed = fixed;
		
	}
	
	public void rotate() {
		angle = (angle+1)%4;
	}

}
