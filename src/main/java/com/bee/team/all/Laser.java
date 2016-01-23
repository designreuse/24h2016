package com.bee.team.all;

import java.util.ArrayList;
import java.util.List;

public class Laser {
	List<Point>	laser	= new ArrayList<Point>();
	Point start;
	Point end;
	
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

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

}
