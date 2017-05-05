package cz.muni.fi.eventsshop.rest.exceptionmappers;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.utils.Utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps every InternalException thrown by Rest to InternalServerError response.
 */
@Provider
public class InternalExceptionMapper implements ExceptionMapper<InternalException> {

	@Override
	public Response toResponse(InternalException e) {
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(Utils.convertThrowableToSimpleJson(e))
				.build();
	}

}
