package com.ibm.nlp.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.nlp.hibernate.PatientDao;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.services.PatientService;

@Path("/patients")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PatientWebService {

	private static PatientService delegate = new PatientDao();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * @param patients
	 * @return
	 */
	@POST
	public Response create(final Patients patients) {
		// TODO: process the given patients
		// you may want to use the following return statement, assuming that
		// Patients#getId() or a similar method
		// would provide the identifier to retrieve the created Patients resource:
		// return
		// Response.created(UriBuilder.fromResource(PatientsEndpoint.class).path(String.valueOf(patients.getId())).build()).build();
		return Response.created(null).build();
	}

	/**
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Integer id) {
		Patients patients = null;
		try {
			patients = delegate.getPatient(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
		if (patients == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			return Response.ok(mapper.writeValueAsString(patients)).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();

		}
	}

	/**
	 * @param startPosition
	 * @param maxResult
	 * @return
	 */
	@GET
	public List<Patients> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		// TODO: retrieve the patientses
		final List<Patients> patientses = null;
		return patientses;
	}

	/**
	 * @param id
	 * @param patients
	 * @return
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Integer id, final Patients patients) {
		// TODO: process the given patients
		return Response.noContent().build();
	}

	/**
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Integer id) {
		// TODO: process the patients matching by the given id
		return Response.noContent().build();
	}

}
