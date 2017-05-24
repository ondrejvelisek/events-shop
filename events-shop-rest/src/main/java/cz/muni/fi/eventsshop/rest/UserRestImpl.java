package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.UserFacade;
import cz.muni.fi.eventsshop.model.User;
import cz.muni.fi.eventsshop.security.AppSecurityContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by ondrejvelisek on 22.5.17.
 */
@ApplicationScoped
@Path("/users")
public class UserRestImpl implements UserRest {

	@Inject
	private UserFacade facade;

	@Override
	public List<User> getAllUsers() throws InternalException {
		return facade.getAllUsers();
	}

	@Override
	public User getUserMe(SecurityContext securityContext) throws InternalException {
		if (securityContext instanceof AppSecurityContext) {
			return ((AppSecurityContext) securityContext).getUser();
		}
		return null;
	}

}
