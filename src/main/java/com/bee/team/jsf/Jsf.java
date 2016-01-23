package com.bee.team.jsf;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.bee.team.common.view.VisitBean;

public class Jsf {

	public static void info(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void info(String clientId, String message) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void warn(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public static void warn(String clientId, String message) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public static void error(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public static void error(String clientId, String message) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public static Object getFromFlash(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}

	public static void putInFlash(String key, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, obj);
	}

	public static Object getFromSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public static VisitBean getVisitBean() {
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("visit") != null) {
			return (VisitBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("visit");
		} else {
			return null;
		}
	}

	public static void putInSession(String key, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, obj);
	}

	public static void removeInSession(String key) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static void forwardFaces(FacesContext fc, String url) {
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		try {
			response.sendRedirect(fc.getExternalContext().getRequestContextPath()
					+ fc.getExternalContext().getRequestServletPath() + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void redirect(String url) throws IOException {
		redirect(FacesContext.getCurrentInstance(), url);
	}

	public static void redirectLogin(String url) throws IOException {
		redirectLogin(FacesContext.getCurrentInstance(), url);
	}

	public static void redirect(FacesContext context, String url) throws IOException {
		String normalizedURL = normalizeRedirectURL(context, url);
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getFlash().setRedirect(true);
		externalContext.redirect(normalizedURL);
	}

	public static void redirectLogin(FacesContext context, String url) throws IOException {
		String normalizedURL = normalizeRedirectURLLogin(context, url);
		context.setViewRoot(context.getApplication().getViewHandler().createView(context, normalizedURL));
		context.getExternalContext().redirect(normalizedURL);
		context.getPartialViewContext().setRenderAll(true);
		context.getPartialViewContext().setPartialRequest(false);
		context.renderResponse();
	}

	private static String normalizeRedirectURLLogin(FacesContext context, String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("/")) {
			url = getRequestContextPath(context) + "/" + url;
		}
		if (url.contains("faces-redirect") || url.contains("logout")) {
			return url;
		}
		return url + "?faces-redirect=true";
	}

	private static String normalizeRedirectURL(FacesContext context, String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("/")) {
			url = getRequestContextPath(context) + "/" + url;
		}
		return url;
	}

	public static String getRequestContextPath(FacesContext context) {
		return context.getExternalContext().getRequestContextPath();
	}
	
	public static String getRemoteAdr() {
		return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
	}
	
	public static String getRemoteAdrToLog() {
		return StringUtils.center(getRemoteAdr(),18);
	}
}
