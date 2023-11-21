package com.ibm.nlp.restservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.nlp.hibernate.MedicationAdverseEventDao;
import com.ibm.nlp.model.mimic3.MedicationAdverseEvent;
import com.ibm.nlp.services.MedicationAdverseEventService;

@Path("/medicationadverseevent")
public class MedicationAdverseEventWebService implements MedicationAdverseEventService {

	private MedicationAdverseEventService delegate = new MedicationAdverseEventDao();

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MedicationAdverseEvent getMedicationAdverseEvent(@QueryParam("id") Integer id) {
		return delegate.getMedicationAdverseEvent(id);
	}

	@Override
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MedicationAdverseEvent> getMedicationAdverseEvents() {
		return delegate.getMedicationAdverseEvents();
	}

	@Override
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public MedicationAdverseEvent saveMedicationAdverseEvent(MedicationAdverseEvent medicationAdverseEvent) {
		return delegate.saveMedicationAdverseEvent(medicationAdverseEvent);
	}

	@Override
	@Path("/delete")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMedicationAdverseEvent(@QueryParam("id") Integer id) {
		delegate.deleteMedicationAdverseEvent(id);
	}

	@Override
	@Path("/clear")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void truncateMedicationAdverseEvents() {
		delegate.truncateMedicationAdverseEvents();
	}

	@Override
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MedicationAdverseEvent> searchMedicationAdverseEventsForText(
			@QueryParam("searchString") String searchString) {
		return delegate.searchMedicationAdverseEventsForText(searchString);
	}

	@Override
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List<String> getAdeMedicationNames() {
		return delegate.getAdeMedicationNames();
	}

}
