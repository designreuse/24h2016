package com.bee.team.app.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.bee.team.app.board.entity.Board;
import com.bee.team.base.BaseDAO;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class BoardDAO extends BaseDAO<Board> {
	private static final String	TABLE_NAME	= "board";
	private static final List<String>	TABLE_PKS	= Arrays.asList("board_id");
	List<String>				fieldList	= Arrays.asList("board_id","level","params");

	public Board findBoardById(String boardId) {
		return getOne("select " + fields() + " from board where board_id = ? ", new BoardMapper(), boardId);
	}

	public List<Board> findAllBoard() {
		return getList("select " + fields() + " from board ", new BoardMapper());
	}

	
	public void createBoard(Board board) {
		save(board);
	}

	public void updateBoard(Board board) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);
		
		f.remove("date_created");
		
		String sql = "update board set " + getUpdateCustomFields(f) + " where board_id = :boardId ";
		update(sql, board);
	}

	public void deleteBoard(String boardId) {
		delete("delete from board where board_id = ?", boardId);
	}

	private final class BoardMapper implements RowMapper<Board> {
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board entity = new Board();
			initEntity(entity, rs);
			return entity;
		}
	}

	protected String getTableName() {
		return TABLE_NAME;
	}

	protected List<String> getTablePKs() {
		return TABLE_PKS;
	}

	protected List<String> getFieldList() {
		return fieldList;
	}
}
