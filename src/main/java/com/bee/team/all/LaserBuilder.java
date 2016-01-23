package com.bee.team.all;

import java.util.List;
import com.bee.team.app.board.entity.Board;

public class LaserBuilder {
	

	
	public void compute(Board board) {
		
		Laser laser = board.getLaser();
		List<Point> path = laser.getPath();
		Point start = laser.getStart();
		int direction = laser.getStartDirection();
		
		path.clear();
		
		handleDirection(board, path,start,direction);
	}
	
	
	private void handleDirection(Board board, List<Point> path, Point point, int direction) {
		
		Point nextPoint = findNextPoint(point,direction);
		System.out.println("nextPoint: "+nextPoint);
		Cell nextCell = board.getCellFromPoint(nextPoint);
		System.out.println("nextCell: "+nextCell);
		if(nextCell==null) return;
		
		String type = nextCell.getType();
		System.out.println("type: "+type);
		int angle = nextCell.getAngle();
		System.out.println("angle: "+angle);
		
		int nextDirection = findNextDirection(type,direction,angle);
		System.out.println("nextDirection: "+nextDirection);
		
		path.add(nextPoint);
		nextCell.setLaserOrigin(nextDirection);
		
		if(nextDirection!=Cell.UNDEFINED)
			handleDirection(board, path, nextPoint, nextDirection);
	}
	
	private Point findNextPoint(Point p, int direction) {
		
		int x = p.getRow();
		int y = p.getColumn();
		
		switch(direction) {
		case Cell.N:x--;break;
		case Cell.S:x++;break;
		case Cell.E:y++;break;
		case Cell.W:y--;break;
		}
		return new Point(x,y);
	}
	
	
	private int findNextDirection(String type, int direction, int angle) {
		
		if(type.equals(Cell.CELL_EMPTY)) return direction;
		if(type.equals(Cell.CELL_LASER_START)) return Cell.UNDEFINED;
		if(type.equals(Cell.CELL_LASER_END)) return Cell.UNDEFINED;
		if(type.equals(Cell.CELL_WALL)) return Cell.UNDEFINED;
		if(type.equals(Cell.CELL_MIRROR)) return reflectOnMirror(direction,angle);
		
		return Cell.UNDEFINED;
	}
	
	private int reflectOnMirror(int direction, int angle) {
		
		if(angle==Cell.N) {
			if(direction==Cell.N) return Cell.UNDEFINED;
			if(direction==Cell.E) return Cell.UNDEFINED;
			if(direction==Cell.S) return Cell.E;
			if(direction==Cell.W) return Cell.N;
		}
		if(angle==Cell.E) {
			if(direction==Cell.N) return Cell.W;
			if(direction==Cell.E) return Cell.UNDEFINED;
			if(direction==Cell.S) return Cell.UNDEFINED;
			if(direction==Cell.W) return Cell.S;
		}
		if(angle==Cell.S) {
			if(direction==Cell.N) return Cell.W;
			if(direction==Cell.E) return Cell.S;
			if(direction==Cell.S) return Cell.UNDEFINED;
			if(direction==Cell.W) return Cell.UNDEFINED;
		}
		if(angle==Cell.W) {
			if(direction==Cell.N) return Cell.UNDEFINED;
			if(direction==Cell.E) return Cell.N;
			if(direction==Cell.S) return Cell.W;
			if(direction==Cell.W) return Cell.UNDEFINED;
		}
		
		return Cell.UNDEFINED;
	}
	
}
