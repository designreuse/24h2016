package com.bee.team.app.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bee.team.base.BaseService;
import com.bee.team.app.board.dao.BoardDAO;
import com.bee.team.app.board.entity.Board;
import com.bee.team.app.user.entity.User;

@Service("boardService")
public class BoardService extends BaseService {

	@Autowired
	BoardDAO	boardDAO;
	
	public Board findBoardById(User currentUser, String boardId) {
		return boardDAO.findBoardById(boardId);
	}

	public List<Board> findAllBoard(User currentUser) {
		return boardDAO.findAllBoard();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createBoard(User currentUser, Board board) {
		boardDAO.createBoard(board);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateBoard(User currentUser, Board board) {
		boardDAO.updateBoard(board);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteBoard(User currentUser, String boardId) {
		boardDAO.deleteBoard(boardId);
	}
	
	
}
