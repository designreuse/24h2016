package com.bee.team.common.view;

import java.io.Serializable;
import com.bee.team.app.space.entity.Space;
import com.bee.team.app.user.entity.User;

public class VisitBean implements Serializable {
	private User	user;
	private Space	space;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}
}
