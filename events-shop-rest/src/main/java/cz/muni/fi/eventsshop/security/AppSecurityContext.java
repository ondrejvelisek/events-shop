package cz.muni.fi.eventsshop.security;

import cz.muni.fi.eventsshop.model.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

/**
 *
 */
public class AppSecurityContext implements SecurityContext {

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
		return user.getRoles().contains(s);
	}

	@Override
	public boolean isSecure() {
		return uriInfo.getRequestUri().getScheme().equals("https");
	}

	@Override
	public String getAuthenticationScheme() {
		return user == null ? null : authScheme;
	}

	public User getUser() {
		return user;
	}
}
