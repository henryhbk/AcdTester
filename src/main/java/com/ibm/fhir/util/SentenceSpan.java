package com.ibm.fhir.util;

import java.io.Serializable;

/**
 * The Class SentenceSpan.
 *
 * @author henry.feldman@ibm.com
 */
public class SentenceSpan implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text. */
	private String text;

	/** The start index. */
	private Integer startIndex;

	/** The end index. */
	private Integer endIndex;

	/**
	 * Instantiates a new sentence span.
	 */
	public SentenceSpan() {
		super();
	}

	/**
	 * Instantiates a new sentence span.
	 *
	 * @param text       the text
	 * @param startIndex the start index
	 * @param endIndex   the end index
	 */
	public SentenceSpan(String text, Integer startIndex, Integer endIndex) {
		super();
		this.text = text;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the start index.
	 *
	 * @return the start index
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * Gets the end index.
	 *
	 * @return the end index
	 */
	public Integer getEndIndex() {
		return endIndex;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Sets the start index.
	 *
	 * @param startIndex the new start index
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Sets the end index.
	 *
	 * @param endIndex the new end index
	 */
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	@Override
	public String toString() {
		return "SentenceSpan [text=" + text + ", startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
	}

}
