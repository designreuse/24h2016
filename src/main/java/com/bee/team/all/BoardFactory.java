package com.bee.team.all;

import org.apache.commons.lang3.NotImplementedException;
import com.bee.team.app.board.entity.Board;

public class BoardFactory {

	public static String EMPTY_BOARD      = "EMPTY_BOARD";
	public static String DEBUG_NO_LASER   = "DEBUG_NO_LASER";
	public static String DEBUG_WITH_LASER = "DEBUG_WITH_LASER";

	public static Board create(final String level) {
		if (EMPTY_BOARD.equals(level)) {
			return createEmptyBoard();
		} else if (DEBUG_NO_LASER.equals(level)) {
			return createDebugBoard(false);
		} else if (DEBUG_WITH_LASER.equals(level)) {
			return createDebugBoard(true);
		}
		throw new NotImplementedException("Unknonw level");
	}

	private static Cell[][] createEmptyCells() {
		Cell[][] cells = new Cell[10][10];
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				cells[y][x] = new Cell(Cell.CELL_EMPTY);
			}
		}
		return cells;
	}

	private static Board createEmptyBoard() {
		Board board = new Board();
		board.setCells(createEmptyCells());
		return board;
	}

	private static Board createDebugBoard(boolean withLaserDrawn) {
		Cell[][] cells = createEmptyCells();

		cells[2][3] = new Cell(Cell.CELL_WALL);

		Point startPoint = new Point(3, 9);
		cells[startPoint.getColumn()][startPoint.getRow()] = new Cell(Cell.CELL_LASER_START, Cell.E);

		if (withLaserDrawn) {
			cells[9][4].setLaserOrigin(Cell.W);
			cells[9][5].setLaserOrigin(Cell.W);
			cells[9][6].setLaserOrigin(Cell.W);

			cells[8][7].setLaserOrigin(Cell.S);
			cells[7][7].setLaserOrigin(Cell.S);
			cells[6][7].setLaserOrigin(Cell.S);
			cells[5][7].setLaserOrigin(Cell.S);
			cells[4][7].setLaserOrigin(Cell.S);
			cells[3][7].setLaserOrigin(Cell.S);
		}

		cells[9][7] = new Cell(Cell.CELL_MIRROR, Cell.W, withLaserDrawn ? Cell.W : Cell.UNDEFINED);

		Point endPoint = new Point(7, 2);
		cells[endPoint.getColumn()][endPoint.getRow()] = new Cell(Cell.CELL_LASER_END, Cell.UNDEFINED, withLaserDrawn ? Cell.S : Cell.UNDEFINED);

		Laser laser = new Laser(startPoint, endPoint, Cell.E);

		Board board = new Board();
		board.setCells(cells);
		board.setLaser(laser);
		return board;
	}

}
