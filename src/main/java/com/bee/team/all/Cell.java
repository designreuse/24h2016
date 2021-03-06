package com.bee.team.all;

import java.io.Serializable;

public class Cell implements Serializable {

	private static final long	serialVersionUID	= 1L;

	public static final String	CELL_EMPTY			= "empty";
	public static final String	CELL_WALL			= "wall";
	public static final String	CELL_MIRROR			= "mirror";
	public static final String	CELL_CHECKPOINT		= "checkpoint";
	public static final String	CELL_BOMB			= "bomb";
	public static final String	CELL_LASER_START	= "lstart";
	public static final String	CELL_LASER_END		= "lend";
	public static final String	CELL_GATE			= "gate";
	public static final String	CELL_SPLIT			= "split";
	public static final int		UNDEFINED			= -1;
	public static final int		N					= 0;
	public static final int		E					= 1;
	public static final int		S					= 2;
	public static final int		W					= 3;

	private String				type				= CELL_EMPTY;
	private int					angle				= UNDEFINED;
	private int					laserV				= UNDEFINED;
	private int					laserH				= UNDEFINED;

	private boolean				fixed				= true;

	public Cell(String type) {
		super();
		this.type = type;
	}

	public Cell(String type, int angle) {
		super();
		this.type = type;
		this.angle = angle;
	}

	public Cell(String type, int angle, int laser) {
		super();
		this.type = type;
		this.angle = angle;
		addLaser(laser);
	}

	public Cell(String type, int angle, int laserV, int laserH) {
		super();
		this.type = type;
		this.angle = angle;
		this.laserV = laserV;
		this.laserH = laserH;
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

	public boolean isSplit() {
		return type.equals(CELL_SPLIT);
	}

	
	public boolean isBomb() {
		return type.equals(CELL_BOMB);
	}

	public boolean isGate() {
		return type.equals(CELL_GATE);
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

	public boolean hasVerticalLaser() {
		return laserV != UNDEFINED;
	}

	public boolean hasHorizontalLaser() {
		return laserH != UNDEFINED;
	}

	public boolean hasDoubleLaser() {
		return laserH != UNDEFINED && laserV != UNDEFINED;
	}

	public boolean hasLaser() {
		return laserH != UNDEFINED || laserV != UNDEFINED;
	}

	public String getImage() {
		if (type.equals(CELL_WALL)) { return "mur"; }
		if (type.equals(CELL_EMPTY)) {
			if (hasDoubleLaser()) return "laser_double";
			if (hasVerticalLaser()) return "laser_v";
			if (hasHorizontalLaser()) return "laser_h";
			return null;
		}
		if (type.equals(CELL_LASER_START)) { return "start"; }
		if (type.equals(CELL_LASER_END)) {
			if (hasLaser()) return "end";
			return "end_point";
		}
		if (type.equals(CELL_MIRROR)) {
			if (hasLaser()) {
				if (isFixed()) {
					return "mirroir_fixed_laser";
				} else {
					return "mirroir_laser";
				}
			} else {
				if (isFixed()) {
					return "mirroir_fixed";
				} else {
					return "mirroir";
				}
			}
		}
		if (type.equals(CELL_CHECKPOINT)) {
			if (hasDoubleLaser()) return "checkpoint_double";
			if (hasVerticalLaser()) return "checkpoint_v";
			if (hasHorizontalLaser()) return "checkpoint_h";
			return "checkpoint";
		}
		if (type.equals(CELL_GATE)) {
			if (hasLaser()) return "gate_laser";
			return "gate";
		}
		if (type.equals(CELL_BOMB)) { return "bomb"; }
		
		if (type.equals(CELL_SPLIT)) {
			if (hasLaser()) {
				if(isFixed()){
					return "split_fixed_laser";
				}
				return "split_laser";
			} else {
				if(isFixed()){
					return "split_fixed";
				}
				return "split";
			}
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

	public int getLaserH() {
		return laserH;
	}

	public void setLaserH(int laserH) {
		this.laserH = laserH;
	}

	public int getLaserV() {
		return laserV;
	}

	public void setLaserV(int laserV) {
		this.laserV = laserV;
	}

	public void addLaser(int laser) {
		if (laser == N || laser == S) laserV = laser;
		if (laser == E || laser == W) laserH = laser;
	}

	public void resetLaser() {
		laserH = UNDEFINED;
		laserV = UNDEFINED;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;

	}

	public void rotate() {
		angle = (angle + 1) % 4;
	}

}
