package com.bee.team.all;

import java.io.Serializable;
import java.util.List;
import com.bee.team.app.board.entity.Board;

public class LaserBuilder implements Serializable {
	
	public final int GAME_OVER = -1;
	public final int INCOMPLETE = 0; 
	public final int COMPLETE = 1;
	

	private static final long serialVersionUID = 1L;

	public int compute(Board board) {

		Laser laser = board.getLaser();
		List<Point> path = laser.getPath();
		Point start = laser.getStart();
		int direction = laser.getStartDirection();

		board.resetLaser();

		return handleDirection(board, path, start, direction);
	}

	private int handleDirection(Board board, List<Point> path, Point point, int direction) {

		Point nextPoint = findNextPoint(point, direction);
		Cell nextCell = board.getCellFromPoint(nextPoint);
		if (nextCell == null) return INCOMPLETE;

		String type = nextCell.getType();
		int angle = nextCell.getAngle();

		int nextDirection = findNextDirection(type, direction, angle);

		path.add(nextPoint);
		nextCell.addLaser(nextDirection);

		if (type.equals(Cell.CELL_LASER_END)) return COMPLETE;
		if (nextDirection == Cell.UNDEFINED) return INCOMPLETE;
		
		if (type.equals(Cell.CELL_GATE)) {
			
			if(nextDirection!=angle) {
				nextCell.resetLaser();
				return INCOMPLETE;
			}
			Cell otherGate = board.findOtherGate(nextCell);
			if(otherGate==null){
				nextCell.resetLaser();
				return INCOMPLETE;
			}
			
			nextPoint = board.getPointFromCell(otherGate);
			nextDirection = (otherGate.getAngle()+2)%4;
			
			path.add(nextPoint);
			otherGate.addLaser(nextDirection);
		}

		return handleDirection(board, path, nextPoint, nextDirection);
	}

	private Point findNextPoint(Point p, int direction) {

		int x = p.getRow();
		int y = p.getColumn();

		switch (direction) {
			case Cell.N:
				x--;
				break;
			case Cell.S:
				x++;
				break;
			case Cell.E:
				y++;
				break;
			case Cell.W:
				y--;
				break;
		}
		return new Point(x, y);
	}

	private int findNextDirection(String type, int direction, int angle) {

		if (type.equals(Cell.CELL_EMPTY)) return direction;
		if (type.equals(Cell.CELL_GATE)) return direction;
		if (type.equals(Cell.CELL_CHECKPOINT)) return direction;
		if (type.equals(Cell.CELL_LASER_END)) return direction;
		if (type.equals(Cell.CELL_LASER_START)) return Cell.UNDEFINED;
		if (type.equals(Cell.CELL_WALL)) return Cell.UNDEFINED;
		if (type.equals(Cell.CELL_MIRROR)) return reflectOnMirror(direction, angle);

		return Cell.UNDEFINED;
	}

	private int reflectOnMirror(int direction, int angle) {

		if (angle == Cell.N) {
			if (direction == Cell.N) return Cell.UNDEFINED;
			if (direction == Cell.E) return Cell.UNDEFINED;
			if (direction == Cell.S) return Cell.E;
			if (direction == Cell.W) return Cell.N;
		}
		if (angle == Cell.E) {
			if (direction == Cell.N) return Cell.E;
			if (direction == Cell.E) return Cell.UNDEFINED;
			if (direction == Cell.S) return Cell.UNDEFINED;
			if (direction == Cell.W) return Cell.S;
		}
		if (angle == Cell.S) {
			if (direction == Cell.N) return Cell.W;
			if (direction == Cell.E) return Cell.S;
			if (direction == Cell.S) return Cell.UNDEFINED;
			if (direction == Cell.W) return Cell.UNDEFINED;
		}
		if (angle == Cell.W) {
			if (direction == Cell.N) return Cell.UNDEFINED;
			if (direction == Cell.E) return Cell.N;
			if (direction == Cell.S) return Cell.W;
			if (direction == Cell.W) return Cell.UNDEFINED;
		}

		return Cell.UNDEFINED;
	}

	
	
}
