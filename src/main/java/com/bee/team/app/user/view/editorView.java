package com.bee.team.app.user.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.all.BoardFactory;
import com.bee.team.all.Cell;
import com.bee.team.all.Laser;
import com.bee.team.all.LaserBuilder;
import com.bee.team.all.Point;
import com.bee.team.app.board.entity.Board;
import com.bee.team.app.board.service.BoardService;
import com.bee.team.base.BaseView;
import com.bee.team.jsf.Jsf;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ViewScoped
@ManagedBean(name = "editorView")
@URLMappings(mappings = { @URLMapping(id = "viewEditor", pattern = "/editor", viewId = "/faces/user/editor.xhtml") })
public class editorView extends BaseView {

	@Autowired
	transient BoardService	boardService;
	Cell[][]				cells;
	List<List<Cell>>		list;
	Cell					cell;

	private LaserBuilder	laserBuilder	= new LaserBuilder();

	private boolean			complete;
	private Board			board;
	private String			level;

	@PostConstruct
	public void init() {
		initBean();
	}

	@URLAction(mappingId = "viewEditor", onPostback = false)
	public void viewUserDashboardOnLoad() {
		board = BoardFactory.create("EMPTY_BOARD");

		cells = board.getCells();

		list = new ArrayList<List<Cell>>();
		for (Cell[] cells2 : cells) {
			List<Cell> l1 = new ArrayList<Cell>();
			for (int i = 0; i < cells2.length; i++) {
				l1.add(cells2[i]);
			}
			list.add(l1);
		}

	}

	public void releaseObject(DragDropEvent ddEvent) {

	}

	public void dropObject(DragDropEvent ddEvent) {
		String id = ddEvent.getDragId();
		id = id.replaceAll("FormContent:", "");
		String ligne = getParam("ligne");
		String colonne = getParam("colonne");
		int x = Integer.valueOf(ligne);
		int y = Integer.valueOf(colonne);
		Cell c = list.get(x).get(y);
		c.setType(id);
		c.setAngle(0);
	}

	public String getOrientation(Cell cell) {
		if (cell.getType().equals(Cell.CELL_EMPTY) || cell.getType().equals(Cell.CELL_WALL)) { return ""; }
		if (cell.getType().equals(Cell.CELL_LASER_END)) {
			if (cell.getLaserOrigin() == Cell.N) { return String.valueOf(Cell.S); }
			if (cell.getLaserOrigin() == Cell.S) { return String.valueOf(Cell.N); }
			if (cell.getLaserOrigin() == Cell.E) { return String.valueOf(Cell.W); }
			if (cell.getLaserOrigin() == Cell.W) { return String.valueOf(Cell.E); }
		}
		return String.valueOf(cell.getAngle());
	}

	public String getImage(Cell cell) {
		return cell.getImage();
	}

	public void rotateCell(int ligne, int colonne) {
		Cell cell = list.get(ligne).get(colonne);
		cell.rotate();
	}

	public void testLaser() {
		Point laserStart = null;
		Point laserEnd = null;
		int direction = 0;
		for (int i = 0; i < 10; i++) {
			for (int y = 0; y < 10; y++) {
				Cell c = cells[i][y];
				if (c.isLaserStart()) {
					laserStart = new Point(i, y);
					direction = c.getAngle();
				}
				if (c.isLaserEnd()) {
					laserEnd = new Point(i, y);
				}
			}
		}
		if (laserStart == null || laserEnd == null) {
			Jsf.error("Un point de depart et d'arrivee est obligatoire");
			return;
		}
		Laser l = new Laser(laserStart, laserEnd, direction);
		board.setLaser(l);
		rebuildLaser();

	}

	public void save() {
		Point laserStart = null;
		Point laserEnd = null;
		int direction = 0;
		for (int i = 0; i < 10; i++) {
			for (int y = 0; y < 10; y++) {
				Cell c = cells[i][y];
				if (c.isLaserStart()) {
					laserStart = new Point(i, y);
					direction = c.getAngle();
				}
				if (c.isLaserEnd()) {
					laserEnd = new Point(i, y);
				}
			}
		}
		if (laserStart == null || laserEnd == null) {
			Jsf.error("Un point de depart et d'arrivee est obligatoire");
			return;
		}
		Laser l = new Laser(laserStart, laserEnd, direction);
		board.setLaser(l);
		board.setLevel(level);
		boardService.createBoard(null, board);
		rebuildLaser();

	}

	private void rebuildLaser() {
		complete = laserBuilder.compute(board);
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public List<List<Cell>> getList() {
		return list;
	}

	public void setList(List<List<Cell>> list) {
		this.list = list;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
