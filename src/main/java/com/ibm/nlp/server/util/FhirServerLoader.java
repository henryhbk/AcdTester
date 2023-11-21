package com.ibm.nlp.server.util;

import java.util.List;

import com.ibm.fhir.client.FHIRClient;
import com.ibm.fhir.threading.AddFhirToNotesThread;
import com.ibm.fhir.threading.ThreadPool;
import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.hibernate.NoteEventDao;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.services.NoteEventService;

public class FhirServerLoader {

	private static NoteEventService noteEventDao = new NoteEventDao();

	public static final Integer PAGESIZE = 5000;

	/** The thread pool. */
	private static ThreadPool threadPool;

	public FhirServerLoader() {
		super();
	}

	public static void main(String[] args) throws InterruptedException {
//		

		List<NoteEvent> noteEventList = noteEventDao.getNoteEventsByPage(1, MimicDateCleaner.PAGESIZE);
		System.out.println(noteEventList.size());

		FHIRClient client = null;
		try {
			client = FhirUtil.setupFhirClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long numberOfNotes = noteEventDao.getNumberOfNoteEventRows();
		Long pageCount = numberOfNotes / PAGESIZE;

		System.out.println("Total threads: " + pageCount);

		threadPool = new ThreadPool(pageCount.intValue(), 8);
		for (int page = 1; page < pageCount.intValue(); page++) {

			System.out.println("thread " + page + " created");
			// threadPool.submitTask(new NoteFhirSaverThread(noteEventDao, page, client));
			threadPool.submitTask(new AddFhirToNotesThread("page " + page, noteEventDao, page));

		}
	}

}
