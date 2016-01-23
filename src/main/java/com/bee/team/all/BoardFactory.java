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

		Point startPoint = new Point(3, 9);
		cells[startPoint.getY()][startPoint.getX()] = new Cell(Cell.CELL_LASER_START, Cell.E);

		cells[9][4].setLaserOrigin(Cell.W);
		cells[9][5].setLaserOrigin(Cell.W);
		cells[9][6].setLaserOrigin(Cell.W);

		cells[9][7] = new Cell(Cell.CELL_MIROR, Cell.W, Cell.W);

		cells[8][7].setLaserOrigin(Cell.S);
		cells[7][7].setLaserOrigin(Cell.S);
		cells[6][7].setLaserOrigin(Cell.S);
		cells[5][7].setLaserOrigin(Cell.S);

		Point endPoint = new Point(2, 7);
		cells[endPoint.getY()][endPoint.getX()] = new Cell(Cell.CELL_LASER_END, Cell.N, Cell.S);

		Laser laser = new Laser(startPoint, endPoint, Cell.E);

		Board board = new Board();
		board.setCells(cells);
		board.setLaser(laser);
		return board;
	}

}
