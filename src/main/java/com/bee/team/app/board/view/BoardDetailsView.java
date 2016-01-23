package com.bee.team.app.board.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.all.BoardFactory;
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
	private transient BoardService boardService;

	private Board board;
	private LaserBuilder laserBuilder = new LaserBuilder();
	
	private boolean complete;
	private List<List<Cell>> list;

	@PostConstruct
	public void init() {
		initBean();
		// userTmp = boardService.findBoardById(getUser(), getParam("boardId"));
		String level = getParam("boardId");
		if (level.equals("1")) {
			board = BoardFactory.create(BoardFactory.DEBUG_NO_LASER);
			board.setLevel("1");
		} else if (level.equals("2")) {
			board = BoardFactory.create(BoardFactory.DEBUG_WITH_LASER);
			board.setLevel("2");
		} else if (level.equals("3")) {
			board = BoardFactory.create(BoardFactory.DEBUG_NO_LASER_2);
			board.setLevel("3");
			rebuildLaser();
		} else if (level.equals("4")) {
			board = BoardFactory.create(BoardFactory.DEBUG_NO_LASER);
			board.setLevel("4");
			rebuildLaser();
		} else {
			board = boardService.findBoardById(null, level);
		}

		Cell[][] cells = board.getCells();

		list = new ArrayList<List<Cell>>();
		for (Cell[] cells2 : cells) {
			List<Cell> l1 = new ArrayList<Cell>();
			for (int i = 0; i < cells2.length; i++) {
				l1.add(cells2[i]);
			}
			list.add(l1);
		}
	}

	public String getOrientation(Cell cell) {
		if (cell.getType().equals(Cell.CELL_EMPTY) || cell.getType().equals(Cell.CELL_WALL)) {
			return "";
		}
		if (cell.getType().equals(Cell.CELL_LASER_END)) {
			if (cell.getLaserOrigin() == Cell.N) {
				return String.valueOf(Cell.S);
			}
			if (cell.getLaserOrigin() == Cell.S) {
				return String.valueOf(Cell.N);
			}
			if (cell.getLaserOrigin() == Cell.E) {
				return String.valueOf(Cell.W);
			}
			if (cell.getLaserOrigin() == Cell.W) {
				return String.valueOf(Cell.E);
			}
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
		if(cell.isMirror()) {
			cell.rotate();
			rebuildLaser();
		}
	}
	
	
	
	public Board getBoard() {
		return board;
	}

	private void rebuildLaser() {
		complete = laserBuilder.compute(board);
	}
}
