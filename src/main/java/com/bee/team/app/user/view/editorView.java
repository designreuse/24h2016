package com.bee.team.app.user.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;
import com.bee.team.all.BoardFactory;
import com.bee.team.all.Cell;
import com.bee.team.app.board.entity.Board;
import com.bee.team.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ViewScoped
@ManagedBean(name = "editorView")
@URLMappings(mappings = { @URLMapping(id = "viewEditor", pattern = "/editor", viewId = "/faces/user/editor.xhtml") })
public class editorView extends BaseView {

	Cell[][]			cells;
	List<List<Cell>>	list;
	Cell				cell;

	@PostConstruct
	public void init() {
		initBean();
	}

	@URLAction(mappingId = "viewEditor", onPostback = false)
	public void viewUserDashboardOnLoad() {
		Board board = BoardFactory.create("EMPTY_BOARD");

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
		
		Cell c = (Cell) ddEvent.getData();
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

}
