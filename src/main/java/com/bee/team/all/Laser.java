package com.bee.team.all;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Laser implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<List<Point>> path;
	private int startDirection;
	private Point start;
	private Point end;

	public Laser(Point start, Point end, int startDirection) {
		this.start = start;
		this.end = end;
		this.startDirection = startDirection;
		this.path = new ArrayList<>();
	}

	public List<List<Point>> getPath() {
		return path;
	}
	
	public ArrayList<Point> createNewPath(List<Point> oldPath){
		ArrayList<Point> newPath = new ArrayList<>();
		newPath.addAll(oldPath);
		path.add(newPath);
		return newPath;
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
		for (List<Point> subpath:path){
			for (Point p : subpath) {
				b.append(p);
			}
		}
		return b.toString();
	}

}
