package com.bee.team.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class AkelioExceptionHandlerFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory	parent;

	public AkelioExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = new AkelioExceptionHandler(parent.getExceptionHandler());
		return result;
	}

}
