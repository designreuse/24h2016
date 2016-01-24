package com.bee.team.app.board.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.all.Cell;
import com.bee.team.all.LaserBuilder;
import com.bee.team.app.board.entity.Board;
import com.bee.team.app.board.service.BoardService;
import com.bee.team.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "boardDetailsView")
@URLMapping(id = "viewBoardDetails", pattern = "/board/#{/\\\\d+/boardId}", viewId = "/faces/board/boardDetails.xhtml")
public class BoardDetailsView extends BaseView implements Serializable {

	@Autowired
	private transient BoardService	boardService;

	private Board					board;
	private LaserBuilder			laserBuilder	= new LaserBuilder();

	private boolean					complete;
	private List<List<Cell>>		list;
	public boolean					notFind;

	@PostConstruct
	public void init() {
		initBean();
		// userTmp = boardService.findBoardById(getUser(), getParam("boardId"));
		String level = getParam("boardId");

		board = boardService.findBoardById(null, level);
		updateState();

		if (board != null) {
			board.setPioche(new ArrayList<Cell>());
			Cell[][] cells = board.getCells();

			list = new ArrayList<List<Cell>>();
			for (Cell[] cells2 : cells) {
				List<Cell> l1 = new ArrayList<Cell>();
				for (int i = 0; i < cells2.length; i++) {
					l1.add(cells2[i]);
				}
				list.add(l1);
			}
			notFind = false;
		} else {
			notFind = true;
		}
	}

	public void releaseObject(DragDropEvent ddEvent) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String left = params.get("class");
		String[] sClass = left.split(" ");
		int col = 0;
		int ligne = 0;
		int item = 0;
		for (String s : sClass) {
			if (StringUtils.startsWith(s, "colonne_")) {
				String c = s.replace("colonne_", "");
				col = Integer.valueOf(c);
			}
			if (StringUtils.startsWith(s, "ligne_")) {
				String c = s.replace("ligne_", "");
				ligne = Integer.valueOf(c);
			}
			if (StringUtils.startsWith(s, "position_")) {
				String c = s.replace("position_", "");
				item = Integer.valueOf(c);
			}
		}
		if (col == 0 && ligne == 0) {
			board.getPioche().remove(item);
		} else {
			Cell c = list.get(ligne).get(col);
			c.setType(Cell.CELL_EMPTY);
			c.setAngle(-1);
		}

		System.out.println("drop");
	}

	public void putInPioche(DragDropEvent ddEvent) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String left = params.get("class");
		String[] sClass = left.split(" ");
		String idTmp = "";
		int colTmp = -1;
		int ligneTmp = 0;
		for (String s : sClass) {
			if (StringUtils.startsWith(s, "colonne_")) {
				String c = s.replace("colonne_", "");
				colTmp = Integer.valueOf(c);
			}
			if (StringUtils.startsWith(s, "ligne_")) {
				String c = s.replace("ligne_", "");
				ligneTmp = Integer.valueOf(c);
			}
			if (StringUtils.startsWith(s, "id_")) {
				idTmp = s.replace("id_", "");
			}
		}
		if (colTmp != -1) {
			Cell c = list.get(ligneTmp).get(colTmp);
			c.setType(Cell.CELL_EMPTY);
			c.setAngle(-1);
		}
		if (idTmp.equals(Cell.CELL_MIRROR)) {
			Cell c = new Cell(idTmp);
			c.setAngle(0);
			board.getPioche().add(c);
		}
		updateState();
	}

	public void dropObject(DragDropEvent ddEvent) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String ligne = getParam("ligne");
		String colonne = getParam("colonne");
		String left = params.get("class");
		String[] sClass = left.split(" ");
		int colTmp = -1;
		int ligneTmp = 0;
		int item = -1;
		String idTmp = "";
		for (String s : sClass) {
			if (StringUtils.startsWith(s, "colonne_")) {
				String c = s.replace("colonne_", "");
				colTmp = Integer.valueOf(c);
			}
			if (StringUtils.startsWith(s, "ligne_")) {
				String c = s.replace("ligne_", "");
				ligneTmp = Integer.valueOf(c);
			}
			if (StringUtils.startsWith(s, "id_")) {
				idTmp = s.replace("id_", "");
			}

			if (StringUtils.startsWith(s, "position_")) {
				String c = s.replace("position_", "");
				item = Integer.valueOf(c);
			}
		}
		int x = Integer.valueOf(ligne);
		int y = Integer.valueOf(colonne);
		String id;
		if (StringUtils.isEmpty(idTmp)) {
			id = params.get("dragId");
			id = id.replaceAll("FormContent:", "");
		} else {
			id = idTmp;
			if (item != -1) board.getPioche().remove(item);
		}
		if (colTmp == -1) {
			Cell newCell = list.get(x).get(y);
			newCell.setType(id);
			newCell.setAngle(0);
		} else {
			Cell c = list.get(ligneTmp).get(colTmp);
			Cell newCell = list.get(x).get(y);
			newCell.setType(c.getType());
			newCell.setAngle(c.getAngle());
			c.setType(Cell.CELL_EMPTY);
			c.setAngle(-1);
		}
		updateState();
	}

	public String getOrientation(Cell cell) {
		if (cell.isEmpty()) return "";
		if (cell.isWall()) return "";
		if (cell.isCheckpoint()) return "";

		if (cell.getType().equals(Cell.CELL_LASER_END)) {
			if (cell.getLaserV() == Cell.N) { return String.valueOf(Cell.S); }
			if (cell.getLaserV() == Cell.S) { return String.valueOf(Cell.N); }
			if (cell.getLaserH() == Cell.E) { return String.valueOf(Cell.W); }
			if (cell.getLaserH() == Cell.W) { return String.valueOf(Cell.E); }
		}
		return String.valueOf(cell.getAngle());
	}

	public String getImage(Cell cell) {
		return cell.getImage();
	}

	public List<List<Cell>> getList() {
		return list;
	}

	public void setList(List<List<Cell>> list) {
		this.list = list;
	}

	public boolean isComplete() {
		return complete;
	}

	public void rotateCell(int ligne, int colonne) {
		Cell cell = list.get(ligne).get(colonne);
		if (cell.isMirror() && !cell.isFixed()) {
			cell.rotate();
			updateState();
		}
	}

	public Board getBoard() {
		return board;
	}

	private void updateState() {
		boolean endReached = laserBuilder.compute(board);
		boolean checkPointsReached = board.checkPointsReached();

		complete = endReached && checkPointsReached;
	}

	public boolean isNotFind() {
		return notFind;
	}

	public void setNotFind(boolean notFind) {
		this.notFind = notFind;
	}
}
