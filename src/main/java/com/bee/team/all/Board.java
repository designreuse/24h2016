package com.bee.team.all;

import org.apache.commons.lang3.StringUtils;

public class Board {
	private Laser laser;
	private Cell[][] cells;

	public void load(final String level) {
		if (StringUtils.isBlank(level)) {
			loadDebugMap();
		}
	}

	private void loadDebugMap() {
		cells = new Cell[10][10];
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				cells[y][x] = new Cell(Cell.CELL_EMPTY);
			}
		}

		cells[2][3] = new Cell(Cell.CELL_WALL);

		Point startPoint = new Point();
		startPoint.setX(3);
		startPoint.setY(9);
		laser = new Laser(new Point(),new Point(),0);
		cells[startPoint.getY()][startPoint.getX()] = new Cell(Cell.CELL_LASER_START);
		cells[startPoint.getY()][startPoint.getX()].setAngle(Cell.E);

		cells[9][7] = new Cell(Cell.CELL_MIROR);
		cells[9][7].setAngle(Cell.W);

		cells[2][7] = new Cell(Cell.CELL_LASER_END);
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
