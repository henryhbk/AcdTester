/**
 * 
 */
package com.ibm.nlp.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.ibm.nlp.hibernate.PrescriptionDao;
import com.ibm.nlp.model.mimic3.Prescription;
import com.ibm.nlp.services.PrescriptionService;

/**
 * @author henryhbk
 *
 */
@Path("/prescriptions")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PrescriptionEndpoint {

	private PrescriptionService prescriptionDao = new PrescriptionDao();

	/**
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Prescription findById(@PathParam("id") final Integer id) {
		// TODO: retrieve the prescription
		Prescription prescription = prescriptionDao.getPrescription(id);
		return prescription;
	}

	/**
	 * @param startPosition
	 * @param maxResult
	 * @return
	 */
	@GET
	public List<Prescription> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		// TODO: retrieve the prescriptions
		final List<Prescription> prescriptions = prescriptionDao.getAllPrescriptions();
		return prescriptions;
	}

	/**
	 * @param id
	 * @param prescription
	 * @return
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Prescription prescription) {
		// TODO: process the given prescription
		return Response.created(null).build();
	}

	/**
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		// TODO: process the prescription matching by the given id
		return Response.noContent().build();
	}

}
