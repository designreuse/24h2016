package com.bee.team.all;

public class Board {
	Laser		laser;
	Cell[][]	cells;

	public void load() {
		cells = new Cell[10][10];
		cells[0][0] = new Cell(Cell.CELL_EMPTY);
		cells[0][1] = new Cell(Cell.CELL_EMPTY);
		cells[0][2] = new Cell(Cell.CELL_EMPTY);
		cells[0][3] = new Cell(Cell.CELL_EMPTY);
		cells[0][4] = new Cell(Cell.CELL_EMPTY);
		cells[0][5] = new Cell(Cell.CELL_EMPTY);
		cells[0][6] = new Cell(Cell.CELL_EMPTY);
		cells[0][7] = new Cell(Cell.CELL_EMPTY);
		cells[0][8] = new Cell(Cell.CELL_EMPTY);
		cells[0][9] = new Cell(Cell.CELL_EMPTY);
		cells[1][0] = new Cell(Cell.CELL_EMPTY);
		cells[1][1] = new Cell(Cell.CELL_EMPTY);
		cells[1][2] = new Cell(Cell.CELL_EMPTY);
		cells[1][3] = new Cell(Cell.CELL_EMPTY);
		cells[1][4] = new Cell(Cell.CELL_EMPTY);
		cells[1][5] = new Cell(Cell.CELL_EMPTY);
		cells[1][6] = new Cell(Cell.CELL_EMPTY);
		cells[1][7] = new Cell(Cell.CELL_EMPTY);
		cells[1][8] = new Cell(Cell.CELL_EMPTY);
		cells[1][9] = new Cell(Cell.CELL_EMPTY);
		cells[2][0] = new Cell(Cell.CELL_EMPTY);
		cells[2][1] = new Cell(Cell.CELL_EMPTY);
		cells[2][2] = new Cell(Cell.CELL_EMPTY);
		cells[2][3] = new Cell(Cell.CELL_EMPTY);
		cells[2][4] = new Cell(Cell.CELL_EMPTY);
		cells[2][5] = new Cell(Cell.CELL_EMPTY);
		cells[2][6] = new Cell(Cell.CELL_EMPTY);
		cells[2][7] = new Cell(Cell.CELL_EMPTY);
		cells[2][8] = new Cell(Cell.CELL_EMPTY);
		cells[2][9] = new Cell(Cell.CELL_EMPTY);
		cells[3][0] = new Cell(Cell.CELL_EMPTY);
		cells[3][1] = new Cell(Cell.CELL_EMPTY);
		cells[3][2] = new Cell(Cell.CELL_EMPTY);
		cells[3][3] = new Cell(Cell.CELL_EMPTY);
		cells[3][4] = new Cell(Cell.CELL_EMPTY);
		cells[3][5] = new Cell(Cell.CELL_EMPTY);
		cells[3][6] = new Cell(Cell.CELL_EMPTY);
		cells[3][7] = new Cell(Cell.CELL_EMPTY);
		cells[3][8] = new Cell(Cell.CELL_EMPTY);
		cells[3][9] = new Cell(Cell.CELL_EMPTY);
		cells[4][0] = new Cell(Cell.CELL_EMPTY);
		cells[4][1] = new Cell(Cell.CELL_EMPTY);
		cells[4][2] = new Cell(Cell.CELL_EMPTY);
		cells[4][3] = new Cell(Cell.CELL_EMPTY);
		cells[4][4] = new Cell(Cell.CELL_EMPTY);
		cells[4][5] = new Cell(Cell.CELL_EMPTY);
		cells[4][6] = new Cell(Cell.CELL_EMPTY);
		cells[4][7] = new Cell(Cell.CELL_EMPTY);
		cells[4][8] = new Cell(Cell.CELL_EMPTY);
		cells[4][9] = new Cell(Cell.CELL_EMPTY);
		cells[5][0] = new Cell(Cell.CELL_EMPTY);
		cells[5][1] = new Cell(Cell.CELL_EMPTY);
		cells[5][2] = new Cell(Cell.CELL_EMPTY);
		cells[5][3] = new Cell(Cell.CELL_EMPTY);
		cells[5][4] = new Cell(Cell.CELL_EMPTY);
		cells[5][5] = new Cell(Cell.CELL_EMPTY);
		cells[5][6] = new Cell(Cell.CELL_EMPTY);
		cells[5][7] = new Cell(Cell.CELL_EMPTY);
		cells[5][8] = new Cell(Cell.CELL_EMPTY);
		cells[5][9] = new Cell(Cell.CELL_EMPTY);
		cells[6][0] = new Cell(Cell.CELL_EMPTY);
		cells[6][1] = new Cell(Cell.CELL_EMPTY);
		cells[6][2] = new Cell(Cell.CELL_EMPTY);
		cells[6][3] = new Cell(Cell.CELL_EMPTY);
		cells[6][4] = new Cell(Cell.CELL_EMPTY);
		cells[6][5] = new Cell(Cell.CELL_EMPTY);
		cells[6][6] = new Cell(Cell.CELL_EMPTY);
		cells[6][7] = new Cell(Cell.CELL_EMPTY);
		cells[6][8] = new Cell(Cell.CELL_EMPTY);
		cells[6][9] = new Cell(Cell.CELL_EMPTY);
		cells[7][0] = new Cell(Cell.CELL_EMPTY);
		cells[7][1] = new Cell(Cell.CELL_EMPTY);
		cells[7][2] = new Cell(Cell.CELL_EMPTY);
		cells[7][3] = new Cell(Cell.CELL_EMPTY);
		cells[7][4] = new Cell(Cell.CELL_EMPTY);
		cells[7][5] = new Cell(Cell.CELL_EMPTY);
		cells[7][6] = new Cell(Cell.CELL_EMPTY);
		cells[7][7] = new Cell(Cell.CELL_EMPTY);
		cells[7][8] = new Cell(Cell.CELL_EMPTY);
		cells[7][9] = new Cell(Cell.CELL_EMPTY);
		cells[8][0] = new Cell(Cell.CELL_EMPTY);
		cells[8][1] = new Cell(Cell.CELL_EMPTY);
		cells[8][2] = new Cell(Cell.CELL_EMPTY);
		cells[8][3] = new Cell(Cell.CELL_EMPTY);
		cells[8][4] = new Cell(Cell.CELL_EMPTY);
		cells[8][5] = new Cell(Cell.CELL_EMPTY);
		cells[8][6] = new Cell(Cell.CELL_EMPTY);
		cells[8][7] = new Cell(Cell.CELL_EMPTY);
		cells[8][8] = new Cell(Cell.CELL_EMPTY);
		cells[8][9] = new Cell(Cell.CELL_EMPTY);
		cells[9][0] = new Cell(Cell.CELL_EMPTY);
		cells[9][1] = new Cell(Cell.CELL_EMPTY);
		cells[9][2] = new Cell(Cell.CELL_EMPTY);
		cells[9][3] = new Cell(Cell.CELL_EMPTY);
		cells[9][4] = new Cell(Cell.CELL_EMPTY);
		cells[9][5] = new Cell(Cell.CELL_EMPTY);
		cells[9][6] = new Cell(Cell.CELL_EMPTY);
		cells[9][7] = new Cell(Cell.CELL_EMPTY);
		cells[9][8] = new Cell(Cell.CELL_EMPTY);
		cells[9][9] = new Cell(Cell.CELL_EMPTY);
	}

	public Laser getLaser() {
		return laser;
	}

	public void setLaser(Laser laser) {
		this.laser = laser;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

}
