package com.bee.team.all;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Laser implements Serializable {

	private List<Point>	path;

	private int			startDirection;
	private Point		start;
	private Point		end;

	public Laser(Point start, Point end, int startDirection) {

		this.start = start;
		this.end = end;
		this.startDirection = startDirection;
		this.path = new ArrayList<>();
	}

	public List<Point> getPath() {
		return path;
	}

	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}

	public int getStartDirection() {
		return startDirection;
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		for (Point p : path) {
			b.append(p);
		}
		return b.toString();
	}
}
