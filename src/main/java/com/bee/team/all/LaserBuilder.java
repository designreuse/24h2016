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
	
	
	private void handleDirection(Board board, List<Point> path, Point p, int direction) {
		Cell currentCell = getCellFromPoint(board,p);
		
		String currentCellType = currentCell.getType();
		
		
	}
	
	
	private Cell getCellFromPoint(Board board, Point p) {
		return board.getCells()[p.getX()][p.getY()];
	}
}
