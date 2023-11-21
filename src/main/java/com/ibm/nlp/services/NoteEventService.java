package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.NoteEventInfo;

// TODO: Auto-generated Javadoc
/**
 * The Interface NoteEventService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface NoteEventService {

	/**
	 * Gets the note event.
	 *
	 * @param id the id
	 * @return the note event
	 */
	NoteEvent getNoteEvent(Integer id);

	/**
	 * Gets the all note events.
	 *
	 * @return the all note events
	 */
	List<NoteEvent> getAllNoteEvents();

	/**
	 * Gets the all note events for mimic study (with the mimicstudy flag set to
	 * true).
	 *
	 * @return the all note events for mimic study
	 */
	List<NoteEvent> getAllNoteEventsForMimicStudy();

	/**
	 * Save note event.
	 *
	 * @param noteEvent the note event
	 * @return the note event
	 */
	NoteEvent saveNoteEvent(NoteEvent noteEvent);

	/**
	 * Delete note event (works for both NoteEvent and NoteEventInfo which is
	 * essentially the same thing just dropping some fields for memory).
	 *
	 * @param id the id
	 */
	void deleteNoteEvent(Integer id);

	/**
	 * Gets the note event info.
	 *
	 * @param id the id
	 * @return the note event info
	 */
	NoteEventInfo getNoteEventInfo(Integer id);

	/**
	 * Gets the all note event infos.
	 *
	 * @return the all note event infos
	 */
	List<NoteEventInfo> getAllNoteEventInfos();

	/**
	 * Gets the all note event infos with an ade.
	 *
	 * @return the all note event infos with ade
	 */
	List<NoteEventInfo> getAllNoteEventInfosWithAde();

	/**
	 * Search notes for text using the database's fulltext index.
	 *
	 * @param text the text
	 * @return the list
	 */
	List<NoteEventInfo> searchNotesForText(String text);

	/**
	 * Gets the note events by page as the data set is massive (1.5m notes)
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the note events by page
	 */
	List<NoteEvent> getNoteEventsByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the number of lab event rows used for the thread paging above.
	 *
	 * @return the number of lab event rows
	 */
	Long getNumberOfNoteEventRows();

}
