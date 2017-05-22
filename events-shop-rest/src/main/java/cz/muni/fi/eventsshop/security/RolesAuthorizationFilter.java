package cz.muni.fi.eventsshop.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * Basically copied from org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature
 */
@Priority(Priorities.AUTHORIZATION)
public class RolesAuthorizationFilter implements ContainerRequestFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final boolean denyAll;
	private final String[] rolesAllowed;

	RolesAuthorizationFilter() {
		this.denyAll = true;
		this.rolesAllowed = null;
	}

	RolesAuthorizationFilter(final String[] rolesAllowed) {
		this.denyAll = false;
		this.rolesAllowed = (rolesAllowed != null) ? rolesAllowed : new String[] {};
	}

	@Override
	public void filter(final ContainerRequestContext requestContext) throws IOException {

		log.trace("Authz security context: " + requestContext.getSecurityContext().getUserPrincipal());

		if (!denyAll) {
			if (rolesAllowed.length > 0 && !isAuthenticated(requestContext)) {
				throw new ForbiddenException();
			}

			for (final String role : rolesAllowed) {
				if (requestContext.getSecurityContext().isUserInRole(role)) {
					return;
				}
			}
		}

		throw new ForbiddenException();
	}

	private static boolean isAuthenticated(final ContainerRequestContext requestContext) {
		return requestContext.getSecurityContext().getUserPrincipal() != null;
	}
}
