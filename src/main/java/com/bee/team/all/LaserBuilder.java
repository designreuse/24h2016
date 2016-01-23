package com.bee.team.all;

public class LaserBuilder {
	

	
	public void compute(Laser laser, Board board) {
		
		laser.reset();
		Point start = laser.getStart();
		int direction = findStartDirection(start);
		
		
	}
	
	
	private int findStartDirection(Point p) {
		return Cell.N;
	}
}
