package com.bee.team.exception;

import com.bee.team.app.user.entity.User;

public class KSecurityException extends java.lang.RuntimeException {

	private User	user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public KSecurityException() {
	}

	public KSecurityException(String message) {
		super(message);
	}

	public KSecurityException(User user) {
		this.user = user;
	}

}
