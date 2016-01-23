package com.bee.team.common.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.bee.team.app.board.entity.Board;
import com.bee.team.app.board.service.BoardService;
import com.bee.team.base.BaseView;

@RequestScoped
@ManagedBean(name = "headerView")
public class HeaderView extends BaseView implements Serializable {

	@Autowired
	private transient BoardService	boardService;
	
	@PostConstruct
	public void init() {
		WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
		if((session==null || getVisit()==null) && !getFacesContext().getPartialViewContext().isAjaxRequest()){
			throw new ViewExpiredException();
		}
	}
	
	public List<Board> getBoardList() {
		return boardService.findAllBoard(null);
	}
	
}
