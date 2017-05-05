package cz.muni.fi.eventsshop.rest.exceptionmappers;

import cz.muni.fi.eventsshop.exceptions.BadCallException;
import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.utils.Utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps every InternalException thrown by Rest to InternalServerError response.
 */
@Provider
public class BeanNotExistsExceptionMapper implements ExceptionMapper<BeanNotExistsException> {

	@Override
	public Response toResponse(BeanNotExistsException e) {
		return Response
				.status(Response.Status.NOT_FOUND)
				.entity(Utils.convertThrowableToSimpleJson(e))
				.build();
	}

}
