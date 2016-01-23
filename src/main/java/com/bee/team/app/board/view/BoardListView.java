package com.bee.team.app.board.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.base.BaseView;
import com.bee.team.app.board.entity.Board;
import com.bee.team.app.board.service.BoardService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "boardListView")
@URLMapping(id = "viewBoardList", pattern = "/board/list", viewId = "/faces/board/boardList.xhtml")
public class BoardListView extends BaseView implements Serializable {

	@Autowired
	private transient BoardService	boardService;

	private List<Board>				boardList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		boardList = boardService.findAllBoard(getUser());
	}
	
	public String deleteBoard() {
		boardService.deleteBoard(getUser(), getParam("boardId"));
		refresh();
		return "";
	}
	
	public List<Board> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}
}
