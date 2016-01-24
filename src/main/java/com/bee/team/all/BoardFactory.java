package com.bee.team.all;

import org.apache.commons.lang3.NotImplementedException;

import com.bee.team.app.board.entity.Board;

public class BoardFactory {

	public static String EMPTY_BOARD = "EMPTY_BOARD";
	public static String DEBUG_NO_LASER = "DEBUG_NO_LASER";
	public static String DEBUG_NO_LASER_2 = "DEBUG_NO_LASER_2";
	public static String DEBUG_WITH_LASER = "DEBUG_WITH_LASER";

	public static Board create(final String level) {
		if (EMPTY_BOARD.equals(level)) {
			return createEmptyBoard(10, 10);
		} else if (DEBUG_NO_LASER.equals(level)) {
			return createDebugBoard(false, 1);
		} else if (DEBUG_NO_LASER_2.equals(level)) {
			return createDebugBoard(false, 2);
		} else if (DEBUG_WITH_LASER.equals(level)) {
			return createDebugBoard(true, 1);
		}
		throw new NotImplementedException("Unknonw level");
	}

	public static Board createEmptyBoard(final int heigth, final int width) {
		Board board = new Board();
		board.resetCells(heigth, width);
		return board;
	}

	private static Board createDebugBoard(boolean withLaserDrawn, int levelId) {
		Board board = new Board();
		board.resetCells(10, 10);
		Cell[][] cells = board.getCells();

		if (levelId == 1) {
			cells[2][3] = new Cell(Cell.CELL_WALL);

			Point startPoint = new Point(9, 3);
			cells[startPoint.getRow()][startPoint.getColumn()] = new Cell(Cell.CELL_LASER_START, Cell.E);

			if (withLaserDrawn) {
				cells[9][4].addLaser(Cell.W);
				cells[9][5].addLaser(Cell.W);
				cells[9][6].addLaser(Cell.W);

				cells[8][7].addLaser(Cell.S);
				cells[7][7].addLaser(Cell.S);
				cells[6][7].addLaser(Cell.S);
				cells[5][7].addLaser(Cell.S);
				cells[4][7].addLaser(Cell.S);
				cells[3][7].addLaser(Cell.S);
			}

			cells[9][7] = new Cell(Cell.CELL_MIRROR, Cell.W, withLaserDrawn ? Cell.W : Cell.UNDEFINED);

			Point endPoint = new Point(2, 7);
			cells[endPoint.getRow()][endPoint.getColumn()] = new Cell(Cell.CELL_LASER_END, Cell.UNDEFINED,
					withLaserDrawn ? Cell.S : Cell.UNDEFINED);

			Laser laser = new Laser(startPoint, endPoint, Cell.E);
			board.setLaser(laser);
			return board;
		} else if (levelId == 2) {
			cells[2][3] = new Cell(Cell.CELL_WALL);
			cells[9][7] = new Cell(Cell.CELL_WALL);

			Point startPoint = new Point(9, 3);
			cells[startPoint.getRow()][startPoint.getColumn()] = new Cell(Cell.CELL_LASER_START, Cell.E);

			cells[9][5] = new Cell(Cell.CELL_MIRROR, Cell.W);
			cells[6][5] = new Cell(Cell.CELL_MIRROR, Cell.E);
			cells[6][7] = new Cell(Cell.CELL_MIRROR, Cell.W);

			Point endPoint = new Point(2, 7);
			cells[endPoint.getRow()][endPoint.getColumn()] = new Cell(Cell.CELL_LASER_END, Cell.UNDEFINED);

			Laser laser = new Laser(startPoint, endPoint, Cell.E);
			board.setLaser(laser);
			return board;
		}

		return null;
	}

}
