package com.bee.team.jsf;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bee.team.app.user.entity.User;
import com.bee.team.common.view.VisitBean;

public class AkelioPhaseListener implements PhaseListener {
	private static final Logger	logger	= LoggerFactory.getLogger(AkelioPhaseListener.class);

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String viewId = context.getViewRoot().getViewId();
		VisitBean visit = (VisitBean) Jsf.getFromSession("visit");
		if (visit != null) {
			User user = visit.getUser();
			StringBuffer sb = new StringBuffer();
			sb.append(Jsf.getRemoteAdrToLog());
			sb.append(user.getLogin());
			sb.append(",");
			sb.append(user.getTenantId());
			sb.append(" ");
			sb.append(" : " + viewId);
			logger.info(sb.toString());
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	public ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

}
