package com.bee.team.common.view;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import com.bee.team.app.space.entity.Space;
import com.bee.team.app.space.service.SpaceService;
import com.bee.team.app.tenant.entity.Tenant;
import com.bee.team.app.tenant.service.TenantService;
import com.bee.team.app.user.entity.User;
import com.bee.team.app.user.service.UserService;
import com.bee.team.base.BaseView;
import com.bee.team.jsf.Jsf;

@RequestScoped
@ManagedBean(name = "authenticationView")
public class AuthenticationView extends BaseView {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationView.class);
	@Autowired
	@Qualifier("authenticationManager")
	private transient AuthenticationManager authenticationManager;
	@Autowired
	private transient TenantService tenantService;
	@Autowired
	private transient UserService userService;
	@Autowired
	private transient SpaceService spaceService;
	private String login;
	private String password;
	private String localeSelected;
	private RequestCache requestCache = new HttpSessionRequestCache();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private SavedRequest savedRequest;
	private boolean expired;
	private String url;

	public AuthenticationView() {
		localeSelected = "fr";
	}

	@PostConstruct
	public void init() {
		initBean();
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null && !savedRequest.getRedirectUrl().endsWith("favicon.ico")
				&& !savedRequest.getRedirectUrl().contains("error")
				&& !savedRequest.getRedirectUrl().contains("index.xhtml")
				&& !StringUtils.equals(savedRequest.getRedirectUrl(),
						getFacesContext().getExternalContext().getRequestContextPath())) {
			expired = true;
			url = savedRequest.getRedirectUrl();
		}
	}

	public String loginAction() {
		Jsf.removeInSession("visit");
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(this.getLogin(), this.getPassword());
			Authentication result = authenticationManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(result);

			User user = (User) result.getPrincipal();
			if (user.getTenantId().equals("0")) {
				logger.info("***** Erreur de Login : {}", login);
				Jsf.error(getMessage("badlogin"));
				return "";
			}

			VisitBean visit = new VisitBean();
			visit.setUser(user);
			List<Space> spaceList = spaceService.findAllSpace(user);
			Space selectedSpace = null;
			if (spaceList.size() > 0) {
				selectedSpace = spaceList.get(0);
				visit.setSpace(selectedSpace);
			}
			Jsf.putInSession("visit", visit);

			Tenant tenant = tenantService.findTenantById(user, user.getTenantId());
			logger.info("{}***** Connexion de ({},{}), ---" + tenant + "--- IP : {} {}",
					StringUtils.center(request.getRemoteAddr(), 18), login, user.getUserId(), user.getLastName(),
					request.getHeader("user-agent"));
			request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			savedRequest = requestCache.getRequest(request, response);

			if (savedRequest != null && !savedRequest.getRedirectUrl().endsWith("favicon.ico")
					&& !savedRequest.getRedirectUrl().contains("/bo/")
					&& !savedRequest.getRedirectUrl().contains("error")
					&& !savedRequest.getRedirectUrl().contains("index.xhtml")
					&& !StringUtils.equals(savedRequest.getRedirectUrl(),
							getFacesContext().getExternalContext().getRequestContextPath())) {
				expired = true;
				url = savedRequest.getRedirectUrl();
			}
			if (url == null) {
				return "pretty:viewUserDashboard";
			} else {
				try {
					Jsf.redirectLogin(url);
				} catch (IOException e) {
					e.printStackTrace();
					Jsf.error(getMessage("error_message1"));
					return "";
				}
			}
		} catch (BadCredentialsException e) {
			logger.info("***** Erreur de Login : {}", login);
			Jsf.error(getMessage("badlogin"));
			return "";
		}
		return "";

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocaleSelected() {
		return localeSelected;
	}

	public void setLocaleSelected(String localeSelected) {
		this.localeSelected = localeSelected;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

}
