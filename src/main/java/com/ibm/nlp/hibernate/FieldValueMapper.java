package com.ibm.nlp.hibernate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * The Class FieldValueMapper is used for multi field searches (like the
 * patientoracle search). As the developer you specify which entity fields to
 * search in, and then use the value as the thing to search for (like JO in the
 * value to find Jones, Jose, John, etc)
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@JsonRootName(value = "FieldValueMapper")
public class FieldValueMapper implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The field name. */
	private String fieldName;

	/** The field value. */
	private String fieldValue;

	/**
	 * Instantiates a new field value mapper.
	 */
	public FieldValueMapper() {
		super();
	};

	/**
	 * Instantiates a new field value mapper.
	 * 
	 * @param fieldName  the field name
	 * @param fieldValue the field value
	 */
	public FieldValueMapper(String fieldName, String fieldValue) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	/**
	 * Gets the field name.
	 * 
	 * @return the field name
	 */
	@JsonProperty("fieldName")
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Gets the field value.
	 * 
	 * @return the field value
	 */
	@JsonProperty("fieldValue")
	public String getFieldValue() {
		return fieldValue;
	}

	/**
	 * Sets the field name.
	 * 
	 * @param fieldName the new field name
	 */
	@JsonSetter("fieldName")
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Sets the field value.
	 * 
	 * @param fieldValue the new field value
	 */
	@JsonSetter("fieldValue")
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
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
		return "FieldValueMapper [fieldName=" + fieldName + ", fieldValue=" + fieldValue + "]";
	}

}
