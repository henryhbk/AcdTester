package com.ibm.nlp.model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class NoteText is the class that holds the note text and associated
 * filename, as well as timing information for data around speed of processing.
 *
 * @author henry.feldman@ibm.com
 */
public class NoteText {

	/** The id. */
	private java.lang.String id;

	/** The file name. */
	private java.lang.String fileName;

	/** The title. */
	private java.lang.String title;

	/** The text. */
	private java.lang.String text;

	/**
	 * The admission ID is used when mapping to MIMIC III to make sure the other
	 * data matches.
	 */
	private Integer admissionID;

	/** The note date. */
	private Date noteDate;

	/** The ms to process. */
	private Long msToProcess;

	/** The json payload. */
	private String jsonPayload;

	/** The version. */
	private Integer version;

	/**
	 * Instantiates a new note text.
	 */
	public NoteText() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new note text.
	 *
	 * @param id          the id
	 * @param fileName    the file name
	 * @param text        the text
	 * @param msToProcess the ms to process
	 * @param jsonPayload the json payload
	 * @param version     the version
	 * @param title       the title
	 * @param admissionID the admission ID
	 * @param noteDate    the note date
	 */
	public NoteText(String id, String fileName, String text, Long msToProcess, String jsonPayload, Integer version,
			String title, Integer admissionID, Date noteDate) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.text = text;
		this.msToProcess = msToProcess;
		this.jsonPayload = jsonPayload;
		this.version = version;
		this.title = title;
		this.admissionID = admissionID;
		this.noteDate = noteDate;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public java.lang.String getText() {
		return text;
	}

	/**
	 * Gets the ms to process.
	 *
	 * @return the ms to process
	 */
	public Long getMsToProcess() {
		return msToProcess;
	}

	/**
	 * Gets the json payload.
	 *
	 * @return the json payload
	 */
	public String getJsonPayload() {
		return jsonPayload;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * Gets the admission ID.
	 *
	 * @return the admission ID
	 */
	public Integer getAdmissionID() {
		return admissionID;
	}

	/**
	 * Gets the note date.
	 *
	 * @return the note date
	 */
	public Date getNoteDate() {
		return noteDate;
	}

	/**
	 * Sets the note date.
	 *
	 * @param noteDate the new note date
	 */
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * Sets the admission ID.
	 *
	 * @param admissionID the new admission ID
	 */
	public void setAdmissionID(Integer admissionID) {
		this.admissionID = admissionID;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Sets the json payload.
	 *
	 * @param jsonPayload the new json payload
	 */
	public void setJsonPayload(String jsonPayload) {
		this.jsonPayload = jsonPayload;
	}

	/**
	 * Sets the ms to process.
	 *
	 * @param msToProcess the new ms to process
	 */
	public void setMsToProcess(Long msToProcess) {
		this.msToProcess = msToProcess;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(java.lang.String text) {
		this.text = text;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NoteText [id=" + id + ", fileName=" + fileName + ", text=" + text + ", msToProcess=" + msToProcess
				+ ", jsonPayload=" + jsonPayload + ", version=" + version + "]";
	}

}
