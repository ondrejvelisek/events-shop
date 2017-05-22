package cz.muni.fi.eventsshop.security;

import cz.muni.fi.eventsshop.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

/**
 *
 */
@Provider
@ApplicationScoped
public class SecurityFeature implements DynamicFeature {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private UserFacade userFacade;

	@Override
	public void configure(final ResourceInfo resourceInfo, final FeatureContext configuration) {
		final Method method = resourceInfo.getResourceMethod();

		// DenyAll on the method take precedence over RolesAllowed and PermitAll
		if (method.isAnnotationPresent(DenyAll.class)) {
			log.trace("Registering Auth filter for method " + method.getName() +
					" annotated with @DenyAll");
			configuration.register(new JwtAuthenticationFilter(userFacade));
			configuration.register(new RolesAuthorizationFilter());
			return;
		}

		// RolesAllowed on the method takes precedence over PermitAll
		RolesAllowed ra = method.getAnnotation(RolesAllowed.class);
		if (ra != null) {
			log.trace("Registering Authentication and Authoriaztion filter" +
					" for method " + method.getName() +
					" annotated with @RolesAllowed");
			configuration.register(new JwtAuthenticationFilter(userFacade));
			configuration.register(new RolesAuthorizationFilter(ra.value()));
			return;
		}

		// PermitAll takes precedence over RolesAllowed on the class
		if (method.isAnnotationPresent(PermitAll.class)) {
			log.trace("Registering Authentication filter for method " + method.getName() +
					" annotated with @PermitAll");
			configuration.register(new JwtAuthenticationFilter(userFacade));
			return;
		}

		// DenyAll can't be attached to classes

		// RolesAllowed on the class takes precedence over PermitAll
		ra = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
		if (ra != null) {
			log.trace("Registering Authentication and Authorization filter" +
					" for method " + method.getName() +
					" placed in class annotated with @RolesAllowed");
			configuration.register(new JwtAuthenticationFilter(userFacade));
			configuration.register(new RolesAuthorizationFilter(ra.value()));
		}
	}
}
