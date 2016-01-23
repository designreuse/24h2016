package com.bee.team.app.user.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.bee.team.all.Board;
import com.bee.team.all.BoardFactory;
import com.bee.team.all.Cell;
import com.bee.team.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ViewScoped
@ManagedBean(name = "userDashboardView")
@URLMappings(mappings = { @URLMapping(id = "viewUserDashboard", pattern = "/home", viewId = "/faces/user/userDashboard.xhtml") })
public class UserDashboardView extends BaseView {

	Cell[][]			cells;
	List<List<Cell>>	list;

	@PostConstruct
	public void init() {
		initBean();
	}

	@URLAction(mappingId = "viewUserDashboard", onPostback = false)
	public void viewUserDashboardOnLoad() {
		Board board = BoardFactory.create("DEBUG");
		
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
}
