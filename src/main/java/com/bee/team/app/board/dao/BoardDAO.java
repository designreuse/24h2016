package com.bee.team.app.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bee.team.all.BoardFactory;
import com.bee.team.all.Cell;
import com.bee.team.all.Laser;
import com.bee.team.all.Point;
import com.bee.team.app.board.entity.Board;
import com.bee.team.base.BaseDAO;
import com.bee.team.util.JsonUtil;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class BoardDAO extends BaseDAO<Board> {

	private static final String TABLE_NAME = "board";
	private static final List<String> TABLE_PKS = Arrays.asList("board_id");
	List<String> fieldList = Arrays.asList("board_id", "level_number", "level_name", "width", "height", "params");

	public Board findBoardById(String boardId) {
		return getOne("select " + fields() + " from board where board_id = ? ", new BoardMapper(), boardId);
	}

	public List<Board> findAllBoard() {
		return getList("select " + fields() + " from board", new BoardMapper());
	}

	public void createBoard(Board board) {
		mapAttributesToParams(board);
		save(board);
	}

	public void updateBoard(Board board) {
		mapAttributesToParams(board);
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
			mapParamsToAttributes(entity);
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

	@SuppressWarnings("unchecked")
	private static void mapParamsToAttributes(Board board) {
		if (board.getParams() != null) {
			Map<String, Object> storage = JsonUtil.getMapFromJson(board.getParams());

			ArrayList<ArrayList<Cell>> cellsArrays = (ArrayList<ArrayList<Cell>>) storage.get("cells");
			Cell[][] cells = new Cell[10][10];
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++) {
					Map<String,Object> values = (Map<String,Object>) cellsArrays.get(row).get(column);
					String type = (String) values.get("type");
					Integer angle = (Integer) values.get("angle");
					Integer laserOrigin = (Integer) values.get("laserOrigin");
					cells[row][column] = new Cell(type, angle, laserOrigin);
				}
			}
			board.setCells(cells);

			Map<String,Map<String,Object>> points = (Map<String,Map<String,Object>>) storage.get("laser");
			Point start = new Point((Integer) points.get("start").get("row"), (Integer) points.get("start").get("column"));
			Point end = new Point((Integer) points.get("end").get("row"), (Integer) points.get("end").get("column"));
			Integer startDirection = (Integer) ((Map<String,Object>) storage.get("laser")).get("startDirection");
			board.setLaser(new Laser(start, end, startDirection));
		}
	}

	private static void mapAttributesToParams(Board board) {
		Map<String, Object> storage = new HashMap<>();
		storage.put("cells", board.getCells());
		storage.put("laser", board.getLaser());
		String json = JsonUtil.getJsonFromMap(storage);
		board.setParams(json);
	}

	public static void main(String[] args) {
		Board board = BoardFactory.create(BoardFactory.DEBUG_WITH_LASER);
		mapAttributesToParams(board);
		System.out.println(board.getParams());
		board.setCells(null);
		board.setLaser(null);
		mapParamsToAttributes(board);
	}

}
