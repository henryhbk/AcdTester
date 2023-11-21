package com.ibm.fhir.threading;

import java.util.List;

import com.ibm.cloud.sdk.core.service.exception.BadRequestException;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.SocialHistory;
import com.ibm.nlp.services.SocialHistoryService;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;
import com.ibm.watson.health.acd.v1.model.ContainerGroup;
import com.ibm.watson.health.acd.v1.model.SectionAnnotation;

public class SectionFinderThread implements Runnable {

	/** The note event. */
	private NoteEvent noteEvent;

	private final AnnotatorForClinicalData service;

	/** The thread name. */
	private final String threadName;

	private final SocialHistoryService socialHistoryDao;

	public SectionFinderThread(NoteEvent noteEvent, AnnotatorForClinicalData service, String threadName,
			SocialHistoryService socialHistoryDao) {
		super();
		this.noteEvent = noteEvent;
		this.service = service;
		this.threadName = threadName;
		this.socialHistoryDao = socialHistoryDao;
	}

	@Override
	public void run() {

		System.out.println(threadName + " thread starting");
		try {
			doNLPViaACD(noteEvent);
		} catch (Exception e) {
			System.out.println(threadName + " crashed");
			e.printStackTrace();
		}
		System.out.println(threadName + " exiting");

	}

	private void doNLPViaACD(final NoteEvent noteText) throws Exception {

		ContainerGroup response = null;

		// wh_acd.ibm_clinical_insights_v1.0_standard_flow
		try {
			response = service.analyzeWithFlow("henry_test_cartridge_v1.0_aap_test_flow", noteText.getText());
			System.out.println(threadName + " ACD query completed");
		} catch (BadRequestException e) {
			java.lang.System.out.println(e.getMessage() + " " + e.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SectionAnnotation> listSections = response.getSectionAnnotations();
		String socialHistoryText = null;
		if (listSections != null) {
			// Example of fetching values from a concept in the list of concepts
			int counter = 0;
			for (SectionAnnotation concept : listSections) {
				// Get the A&P section and get it's text as then we will be parsing that into a
				// list of conditions to then look inside
				if (concept.getTrigger().getSectionNormalizedName().equals("Social history")) {
					Long begin = concept.getTrigger().getEnd();
					begin = begin + 1L; // offset the : at the end of the name
					// if A&P is the last section then we need to do a different range
					Long end;
					if (listSections.size() == counter) {
						SectionAnnotation nextAnnotation = listSections.get(counter + 1);
						end = nextAnnotation.getTrigger().getEnd();
					} else {
						end = new Long(noteText.getText().length());
					}
					// get the substring that is the A&P section
					socialHistoryText = noteText.getText().substring(begin.intValue(), end.intValue());
				}
				counter++;
			}

			// SO now we can have the plan into a hashmap based on their covered texts from
			// the diagnosis list from the cartridge. This is because ACD currently doesn't
			// pull the associated plan with the problems
			SocialHistory socialHistory = new SocialHistory(noteText.getSubjectId(), socialHistoryText,
					noteText.getHadmId(), socialHistoryText);
			socialHistoryDao.saveSocialhistory(socialHistory);
		}
	}

}
