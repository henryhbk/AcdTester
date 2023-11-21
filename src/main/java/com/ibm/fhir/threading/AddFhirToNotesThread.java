package com.ibm.fhir.threading;

import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.server.util.MimicDateCleaner;
import com.ibm.nlp.services.NoteEventService;

/**
 * The Class AddFhirToNotesThread.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class AddFhirToNotesThread implements Runnable {

	/** The thread name. */
	private final String threadName;

	/** The note event dao. */
	private final NoteEventService noteEventDao;

	/** The page. */
	private final Integer page;

	/**
	 * Instantiates a new adds the fhir to notes thread.
	 *
	 * @param threadName    the thread name
	 * @param noteEventList the note event list
	 * @param noteEventDao  the note event dao
	 * @param page          the page
	 */
	public AddFhirToNotesThread(String threadName, NoteEventService noteEventDao, Integer page) {
		super();
		this.threadName = threadName;
		this.noteEventDao = noteEventDao;
		this.page = page;
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		fixNotes();

	}

	/**
	 * Fix notes.
	 */
	private void fixNotes() {
		System.out.println("STARTING THREAD: " + threadName);
		for (NoteEvent noteEvent : noteEventDao.getNoteEventsByPage(page, MimicDateCleaner.PAGESIZE)) {
			noteEvent.getFhirJson().replace("\"id\": \"12345\"", noteEvent.getSubjectId() + "\"");
			noteEventDao.saveNoteEvent(noteEvent);
		}
		System.out.println("COMPLETED THREAD: " + threadName);
	}

}
