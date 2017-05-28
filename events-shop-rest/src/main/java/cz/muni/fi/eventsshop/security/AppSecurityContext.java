package cz.muni.fi.eventsshop.security;

import cz.muni.fi.eventsshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

/**
 *
 */
public class AppSecurityContext implements SecurityContext {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Context
	private UriInfo uriInfo;

	private String authScheme;
	private User user;
	private Principal userPrincipal;

	public AppSecurityContext(String authScheme, User user, Principal userPrincipal) {
		this.authScheme = authScheme;
		this.user = user;
		this.userPrincipal = userPrincipal;
	}

	@Override
	public Principal getUserPrincipal() {
		return userPrincipal;
	}

	@Override
	public boolean isUserInRole(String s) {
		log.trace("checking if user "+user+" has role "+s);
		return user.getRoles().contains(User.Role.valueOf(s));
	}

	@Override
	public boolean isSecure() {
		return uriInfo.getRequestUri().getScheme().equals("https");
	}

	@Override
	public String getAuthenticationScheme() {
		return user == null ? null : authScheme;
	}

}
