package com.ibm.nlp;

import java.util.ArrayList;
import java.util.List;

import com.ibm.fhir.threading.SectionFinderThread;
import com.ibm.fhir.threading.ThreadPool;
import com.ibm.nlp.hibernate.NoteEventDao;
import com.ibm.nlp.hibernate.SocialHistoryDao;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.services.NoteEventService;
import com.ibm.nlp.services.SocialHistoryService;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;

public class FindSocialHistoryUtil {

	/** The note events. */
	private static List<NoteEvent> noteEvents = new ArrayList<NoteEvent>();

	/** The thread pool. */
	private static ThreadPool threadPool;

	private static AnnotatorForClinicalData service;

	private static NoteEventService noteEventDao = new NoteEventDao();

	private static SocialHistoryService socialHistoryDao = new SocialHistoryDao();

	public static void main(String[] args) throws InterruptedException {

		service = AcdConfugurator.getAcdServiceInstance();

		System.out.println("Starting Data Load of MIMIC ACD Study data");
		try {
			noteEvents = noteEventDao.getAllNoteEventsForMimicStudy();
			System.out.println(noteEvents.size() + " notes read");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Creating Thread Pool");
		threadPool = new ThreadPool(noteEvents.size(), 10);
		Integer counter = 0;

		for (NoteEvent noteEvent : noteEvents) {
			threadPool.submitTask(new SectionFinderThread(noteEvent, service, "thread-" + counter++, socialHistoryDao));
		}
	}

}
