package com.bee.team.all;

import org.apache.commons.lang3.NotImplementedException;

public class BoardFactory {

	public static Board create(final String level) {
		if ("DEBUG".equals(level)) {
			return createDebugBoard();
		}
		throw new NotImplementedException("Unknonw level");
	}

	private static Board createDebugBoard() {
		Cell[][] cells = new Cell[10][10];
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				cells[y][x] = new Cell(Cell.CELL_EMPTY);
			}
		}

		cells[2][3] = new Cell(Cell.CELL_WALL);

		cells[9][7] = new Cell(Cell.CELL_MIROR);
		cells[9][7].setAngle(Cell.W);

		Point startPoint = newPoint(3, 9);
		cells[startPoint.getY()][startPoint.getX()] = new Cell(Cell.CELL_LASER_START);
		cells[startPoint.getY()][startPoint.getX()].setAngle(Cell.E);

		Point endPoint = newPoint(2, 7);
		cells[endPoint.getY()][endPoint.getX()] = new Cell(Cell.CELL_LASER_END);

		Laser laser = new Laser(startPoint, endPoint, Cell.E);

		Board board = new Board();
		board.setCells(cells);
		board.setLaser(laser);
		return board;
	}

	private static Point newPoint(int x, int y) {
		Point point = new Point();
		point.setX(x);
		point.setY(y);
		return point;
	}

}
