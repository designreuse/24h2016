package com.bee.team.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.bee.team.app.user.entity.User;

@Component
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

	private static final Logger	logger	= LoggerFactory.getLogger(LogoutHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if(authentication != null){
			User user = (User) authentication.getPrincipal();
			if (user != null) {
				logger.info(StringUtils.center(request.getRemoteAddr(), 18) + "***** logged OUT successfully (" + user.getLogin() + "," + user.getTenantId() + ")");
			} else {
				logger.info(StringUtils.center(request.getRemoteAddr(), 18) + "***** logged OUT successfully");
			}
		}
		this.setDefaultTargetUrl("/");
		super.onLogoutSuccess(request, response, authentication);
	}
}
