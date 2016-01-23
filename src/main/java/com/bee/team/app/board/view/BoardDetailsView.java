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
	private transient BoardService	boardService;

	private Board					userTmp;

	List<List<Cell>>				list;

	@PostConstruct
	public void init() {
		initBean();
		// userTmp = boardService.findBoardById(getUser(), getParam("boardId"));
		String level = getParam("boardId");
		Board board = null;
		if (level.equals("1")) {
			board = BoardFactory.create(BoardFactory.DEBUG_NO_LASER);
		} else if (level.equals("2")) {
			board = BoardFactory.create(BoardFactory.DEBUG_WITH_LASER);
		} else {
			board = BoardFactory.create(BoardFactory.DEBUG_NO_LASER);
			new LaserBuilder().compute(board);
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

	public String getImage(Cell cell) {
		return cell.getImage();
	}

	public Board getUserTmp() {
		return userTmp;
	}

	public void setUserTmp(Board userTmp) {
		this.userTmp = userTmp;
	}

	public List<List<Cell>> getList() {
		return list;
	}

	public void setList(List<List<Cell>> list) {
		this.list = list;
	}
}
