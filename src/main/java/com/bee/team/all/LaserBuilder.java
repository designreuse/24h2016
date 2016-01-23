package com.bee.team.all;

import java.util.List;

public class LaserBuilder {
	

	
	public void compute(Laser laser, Board board) {
		
		List<Point> path = laser.getPath();
		Point start = laser.getStart();
		int direction = laser.getStartDirection();
		
		path.clear();
		
		handleDirection(board, path,start,direction);
	}
	
	
	private void handleDirection(Board board, List<Point> path, Point point, int direction) {
		
		Point nextPoint = findNextPoint(point,direction);
		Cell nextCell = board.getCellFromPoint(nextPoint);
		String type = nextCell.getType();
		int nextDirection = findNextDirection(type,direction);
		
		
	}
	
	private Point findNextPoint(Point p, int direction) {
		int x = p.getX();
		int y = p.getY();
		switch(direction) {
		case Cell.N:x--;break;
		case Cell.S:x++;break;
		case Cell.E:y++;break;
		case Cell.W:y--;break;
		}
		return new Point(x,y);
	}
	
	
	private int findNextDirection(String type, int direction) {
		if(type.equals(Cell.CELL_EMPTY)) return direction;
		if(type.equals(Cell.CELL_LASER_START)) return -1;
		if(type.equals(Cell.CELL_LASER_END)) return -1;
		if(type.equals(Cell.CELL_WALL)) return -1;
		if(type.equals(Cell.CELL_MIRROR)) return -1;
		return 0;
	}
	
}
