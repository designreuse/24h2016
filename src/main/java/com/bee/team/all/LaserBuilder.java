package com.bee.team.all;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.bee.team.app.board.entity.Board;

public class LaserBuilder implements Serializable {
	
	public final int GAME_OVER = -1;
	public final int INCOMPLETE = 0; 
	public final int COMPLETE = 1;
	

	private static final long serialVersionUID = 1L;

	public int compute(Board board) {

		Laser laser = board.getLaser();
		ArrayList<Point> path = laser.createNewPath(new ArrayList<Point>());
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
		
		
		if(type.equals(Cell.CELL_SPLIT)){
			
			
			if(direction!=angle) {
				nextCell.resetLaser();
				return INCOMPLETE;
			}
			
			ArrayList<Point> newPath = (ArrayList<Point>) board.getLaser().createNewPath(path);
			

			int res = -10;
			
			
					int nextDirection1 = (direction+3)%4;
					nextCell.addLaser(nextDirection1);

					Point nextPoint1 = findNextPoint(nextPoint, nextDirection1);
					Cell nextCell1 = board.getCellFromPoint(nextPoint1);
					
					path.add(nextPoint1);
					nextCell1.addLaser(nextDirection1);
					
					int res1 = handleDirection(board, path, nextPoint1, nextDirection1);
					
					 if(res1==COMPLETE){
						res = res1;
					}
					else if(res1==GAME_OVER && res != COMPLETE){
						res = res1;
					}
					else if(res1==INCOMPLETE && res != COMPLETE && res!=GAME_OVER){
						res = res1;
					}
					int nextDirection2 = (direction+1)%4;

					Point nextPoint2 = findNextPoint(nextPoint, nextDirection2);
					Cell nextCell2 = board.getCellFromPoint(nextPoint2);
					
					newPath.add(nextPoint2);
					nextCell2.addLaser(nextDirection2);
					
					res1 = handleDirection(board, newPath, nextPoint2, nextDirection2);
					if(res1==COMPLETE){
						res = res1;
					}
					else if(res1==GAME_OVER  && res != COMPLETE){
						res = res1;
					}
					else if(res1==INCOMPLETE && res != COMPLETE && res!=GAME_OVER){
						res = res1;
					}
					
					
				
			return res;
		}	

		int nextDirection = findNextDirection(type, direction, angle);

		path.add(nextPoint);
		nextCell.addLaser(nextDirection);

		if (type.equals(Cell.CELL_LASER_END)) return COMPLETE;
		if(type.equals(Cell.CELL_BOMB)) return GAME_OVER;
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
		if (type.equals(Cell.CELL_BOMB)) return direction;
		if (type.equals(Cell.CELL_LASER_END)) return direction;
		if (type.equals(Cell.CELL_LASER_START)) return Cell.UNDEFINED;
		if (type.equals(Cell.CELL_WALL)) return Cell.UNDEFINED;
		if (type.equals(Cell.CELL_MIRROR)) return reflectOnMirror(direction, angle);
		if (type.equals(Cell.CELL_SPLIT)) return reflectOnMirror(direction, angle);

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
