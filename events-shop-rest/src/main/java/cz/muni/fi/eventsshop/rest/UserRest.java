package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Rest is responsible for formating and parsing data.
 * It does not manage Transaction so it SHOULD call only ONE facade method.
 */
public interface UserRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN"})
	List<User> getAllUsers() throws InternalException;

	@GET
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	User getUserMe(@Context SecurityContext securityContext) throws InternalException;

}
