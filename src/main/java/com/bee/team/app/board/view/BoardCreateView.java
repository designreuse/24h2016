package com.bee.team.app.board.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import com.bee.team.app.board.entity.Board;
import com.bee.team.app.board.service.BoardService;
import com.bee.team.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@RequestScoped
@ManagedBean(name = "boardCreateView")
@URLMapping(id = "viewBoardCreate", pattern = "/board/create", viewId = "/faces/board/boardCreate.xhtml")
public class BoardCreateView extends BaseView implements Serializable {

	@Autowired
	private transient BoardService	boardService;

	private Board					userTmp;

	@PostConstruct
	public void init() {
		initBean();
		userTmp = new Board();
	}

	public String createBoard() {
		
		boardService.createBoard(getUser(), userTmp);
		return "pretty:viewBoardList";
	}

	public Board getUserTmp() {
		return userTmp;
	}

	public void setUserTmp(Board userTmp) {
		this.userTmp = userTmp;
	}

}
