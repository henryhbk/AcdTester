package com.ibm.fhir.threading;

import java.util.List;

import com.ibm.fhir.client.FHIRClient;
import com.ibm.fhir.client.FHIRResponse;
import com.ibm.fhir.model.resource.DocumentReference;
import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.server.util.MimicDateCleaner;
import com.ibm.nlp.services.NoteEventService;

public class NoteFhirSaverThread implements Runnable {

	private final NoteEventService noteEventDao;

	private final Integer page;

	private final FHIRClient client;

	private List<NoteEvent> noteEventList;

	private Integer currentRowId;

	/** The Constant OK. */
	private final static int OK = 200;

	/** The Constant CREATED. */
	private final static int CREATED = 201;

	public NoteFhirSaverThread(NoteEventService noteEventDao, Integer page, FHIRClient client) {
		super();
		this.noteEventDao = noteEventDao;
		this.page = page;
		this.client = client;
	}

	@Override
	public void run() {
		sendNotesToServer();
	}

	private synchronized void sendNotesToServer() {
		FHIRResponse response = null;
		noteEventList = noteEventDao.getNoteEventsByPage(page, MimicDateCleaner.PAGESIZE);
		if (!noteEventList.isEmpty()) {
			System.out.println("Page " + page + " with " + noteEventList.size() + " notes");
			for (NoteEvent noteEvent : noteEventList) {
				currentRowId = noteEvent.getRowId();
				DocumentReference documentReference = FhirUtil.getNoteAsFhirResource(noteEvent);
				try {
					response = client.create(documentReference);
					if (response.getStatus() == CREATED || response.getStatus() == OK) {
						// System.out.println("Patient resource was persisted, location = " +
						// response.getLocation());
					} else {
						System.err.println("Error persisting patient, status code = " + response.getStatus());
					}
				} catch (Exception e) {
					System.out.println("Failed to submit noteevent{" + currentRowId + "}");
					e.printStackTrace();
				}

			}
			System.out.println("Completed Page: " + page);
		} else {
			System.out.println("PAGE " + page + " had an empty query");
		}
	}
}
