package com.ibm.nlp.restservices;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The Class EntityNotFoundException for mapping out from a webservivce that the
 * id returned null
 */
@Provider
public class EntityNotFoundException extends Exception implements ExceptionMapper<IllegalArgumentException> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new entity not found exception.
	 */
	public EntityNotFoundException() {
		super();
	}

	/**
	 * Instantiates a new entity not found exception.
	 *
	 * @param arg0 the arg 0
	 */
	public EntityNotFoundException(String arg0) {
		super(arg0);
	}

	/**
	 * To response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@Override
	public Response toResponse(IllegalArgumentException exception) {
		return Response.status(500).entity(exception.getMessage()).type("text/plain").build();
	}

}
