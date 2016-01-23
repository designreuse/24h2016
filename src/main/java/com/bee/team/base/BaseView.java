package com.bee.team.base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.bee.team.app.user.entity.User;
import com.bee.team.common.view.VisitBean;
import com.bee.team.jsf.Jsf;

public abstract class BaseView implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(BaseView.class);

	public void initBean() {
		WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		if (getServletContext() != null) {
			WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext())
					.getAutowireCapableBeanFactory().autowireBean(this);
		}
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public ServletContext getServletContext() {
		if (getFacesContext() == null) {
			return null;
		}
		return (ServletContext) getFacesContext().getExternalContext().getContext();
	}

	public String getParam(String param) {
		return getFacesContext().getExternalContext().getRequestParameterMap().get(param);
	}

	public User getUser() {
		VisitBean visit = Jsf.getVisitBean();
		if (visit == null) {
			User user = new User();
			user.setTenantId("1");
			return user;
		} else {
			return visit.getUser();
		}
	}

	public String getTenantId() {
		if (getUser() == null) {
			return null;
		} else {
			return getUser().getTenantId();
		}
	}

	public ResourceBundle getBundle() {
		Locale loc = new Locale("fr");
		return this.getBundle(loc);
	}

	public ResourceBundle getBundle(Locale locale) {
		return ResourceBundle.getBundle("messages", locale);
	}

	public String getMessageWithLocale(String key, Locale locale) {
		String message;
		try {
			message = getBundle(locale).getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public String getMessage(String key) {
		String message;
		try {
			message = getBundle().getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public String getMessageFormatted(String key, String... param) {
		String message;
		try {
			message = getBundle().getString(key);
			message = MessageFormat.format(message, (Object) param);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public String getConfig(String key) {
		String message;
		try {
			message = ResourceBundle.getBundle("application").getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public String getFormattedMessage(String key, String param) {
		String message;
		try {
			message = getBundle().getString(key);
			message = MessageFormat.format(message, param);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public String getLocaleMessage(String key, String locale) {
		String message;
		try {
			Locale loc = new Locale(locale);
			message = getBundle(loc).getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public VisitBean getVisit() {
		return Jsf.getVisitBean();
	}

}
