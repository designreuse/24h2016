package com.bee.team.all;

import java.io.Serializable;
import com.bee.team.app.user.entity.User;

public class Game implements Serializable {
	User	user;
	int		level;
	int		topLevel;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(int topLevel) {
		this.topLevel = topLevel;
	}

}
