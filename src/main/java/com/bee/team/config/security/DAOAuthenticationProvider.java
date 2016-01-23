package com.bee.team.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.bee.team.app.tenant.entity.Tenant;
import com.bee.team.app.tenant.service.TenantService;
import com.bee.team.app.user.entity.User;
import com.bee.team.app.user.service.UserService;

public class DAOAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserService userService;
	@Autowired
	private TenantService tenantService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = userService.findUserNotDisableByLogin(name);
		if (user != null) {
			Tenant tenant = tenantService.findTenantById(user, user.getTenantId());
			if (true) {
				if (password.equals(user.getPassword())) {
					if (true) {
						List<GrantedAuthority> grantedAuths = new ArrayList<>();
						grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
						return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
					} else {
						throw new BadCredentialsException("User is disabled");
					}

				} else {
					throw new BadCredentialsException("Enter a valid username and password");
				}
			} else {
				throw new BadCredentialsException("Tenant is disabled");
			}
		} else {
			throw new BadCredentialsException("Enter a valid username and password");
		}

	}

	@Override
	/*
	 * Determines if this class can support the token provided by the filter.
	 */
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public String getConfig(String key) {
		String message;
		try {
			message = ResourceBundle.getBundle("application").getString(key);
		} catch (java.util.MissingResourceException mre) {
			// logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

}