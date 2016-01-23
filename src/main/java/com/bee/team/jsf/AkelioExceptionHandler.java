package com.bee.team.jsf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import com.bee.team.app.user.entity.User;
import com.bee.team.exception.KSecurityException;
import com.bee.team.exception.ObjectNotFoundException;

public class AkelioExceptionHandler extends ExceptionHandlerWrapper {
	private static final Logger	logger	= LoggerFactory.getLogger(AkelioExceptionHandler.class);
	private ExceptionHandler	wrapped;

	public AkelioExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		// boolean sessionExpired = false;

		// Iterate over all unhandeled exceptions
		Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext eventContext = (ExceptionQueuedEventContext) event.getSource();

			// obtain throwable object
			Throwable throwable = eventContext.getException();
			Throwable rootThrowable = ExceptionUtils.getRootCause(throwable);
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				if (rootThrowable == null && throwable != null && (throwable.getMessage() == null || (throwable.getMessage() != null && throwable.getMessage().startsWith("null")))) {
					logger.info("Error Session Expired null object");
				} else if (rootThrowable instanceof KSecurityException) {
					if (((KSecurityException) rootThrowable).getUser() != null) {
						User user = ((KSecurityException) rootThrowable).getUser();
						logger.info(user + " Error KSecurityException");
					} else {
						logger.info("Error KSecurityException");
					}
					NavigationHandler navigation = context.getApplication().getNavigationHandler();
					context.getExternalContext().getRequestMap().put("exception", rootThrowable);
					navigation.handleNavigation(context, "", "notAuthorized");
				} else if (rootThrowable instanceof EmptyResultDataAccessException) {
					logger.info(StringUtils.center(((HttpServletRequest) context.getExternalContext().getRequest()).getRemoteAddr(), 20) + " Error EmptyResultDataAccessException");
					NavigationHandler navigation = context.getApplication().getNavigationHandler();
					context.getExternalContext().getRequestMap().put("exception", rootThrowable);
					navigation.handleNavigation(context, "", "objectNotFound");
				} else if (rootThrowable instanceof FileNotFoundException) {
					logger.info(StringUtils.center(((HttpServletRequest) context.getExternalContext().getRequest()).getRemoteAddr(), 20) + " Error FileNotFoundException " + rootThrowable.getMessage());
					NavigationHandler navigation = context.getApplication().getNavigationHandler();
					context.getExternalContext().getRequestMap().put("exception", rootThrowable);
					navigation.handleNavigation(context, "", "objectNotFound");
				} else if (rootThrowable instanceof ObjectNotFoundException) {
					logger.info(StringUtils.center(((HttpServletRequest) context.getExternalContext().getRequest()).getRemoteAddr(), 20) + " Error ObjectNotFoundException "
							+ rootThrowable.getMessage());
					NavigationHandler navigation = context.getApplication().getNavigationHandler();
					context.getExternalContext().getRequestMap().put("exception", rootThrowable);
					navigation.handleNavigation(context, "", "objectNotFound");
				} else if (throwable instanceof ViewExpiredException || rootThrowable instanceof ViewExpiredException) {
					logger.info(StringUtils.center(((HttpServletRequest) context.getExternalContext().getRequest()).getRemoteAddr(), 20) + " Error ViewExpiredException");
					String errorPageLocation = "/error/session.xhtml";
					context.setViewRoot(context.getApplication().getViewHandler().createView(context, errorPageLocation));
					context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/error/session.xhtml");
					context.getPartialViewContext().setRenderAll(true);
					context.renderResponse();
				} else {
					logger.error(StringUtils.center(((HttpServletRequest) context.getExternalContext().getRequest()).getRemoteAddr(), 20) + " Erreur " + throwable.getMessage());
					NavigationHandler navigation = context.getApplication().getNavigationHandler();
					navigation.handleNavigation(context, "", "error");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				i.remove();
			}
		}
		// let the parent handle the rest
		getWrapped().handle();
	}

}
