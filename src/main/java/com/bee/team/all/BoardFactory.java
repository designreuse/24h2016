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

		Point startPoint = new Point();
		startPoint.setX(3);
		startPoint.setY(9);
		Point endPoint = new Point();
		endPoint.setX(3);
		endPoint.setY(9);
//		Laser laser = new Laser();

		cells[startPoint.getY()][startPoint.getX()] = new Cell(Cell.CELL_LASER_START);
		cells[startPoint.getY()][startPoint.getX()].setAngle(Cell.E);

		cells[9][7] = new Cell(Cell.CELL_MIROR);
		cells[9][7].setAngle(Cell.W);

		cells[2][7] = new Cell(Cell.CELL_LASER_END);

		Board board = new Board();
		board.setCells(cells);
		return board;
	}

}
