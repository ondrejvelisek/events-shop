package cz.muni.fi.eventsshop.rest.exceptionmappers;

import cz.muni.fi.eventsshop.exceptions.BeanAlreadyExistsException;
import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.utils.Utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps every InternalException thrown by Rest to InternalServerError response.
 */
@Provider
public class BeanAlreadyExistsExceptionMapper implements ExceptionMapper<BeanAlreadyExistsException> {

	@Override
	public Response toResponse(BeanAlreadyExistsException e) {
		return Response
				.status(Response.Status.CONFLICT)
				.entity(Utils.convertThrowableToSimpleJson(e))
				.build();
	}

}
