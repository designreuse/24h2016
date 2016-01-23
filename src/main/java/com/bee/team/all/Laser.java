package com.bee.team.all;

import java.util.ArrayList;
import java.util.List;

public class Laser {
	List<Point>	laser	= new ArrayList<Point>();

	public Laser(Point p) {
		this.laser = new ArrayList<Point>();
		this.laser.add(p);
	}

	public List<Point> getLaser() {
		return laser;
	}

	public void setLaser(List<Point> laser) {
		this.laser = laser;
	}

}
