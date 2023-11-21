package com.ibm.nlp.model.mimic3;

/**
 * The Interface HasFhir.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface HasFhir {

	/**
	 * Gets the fhir json.
	 *
	 * @return the fhir json
	 */
	public String getFhirJson();

	/**
	 * Sets the fhir json.
	 *
	 * @param fhirJson the fhir json
	 * @return the string
	 */
	public void setFhirJson(String fhirJson);

}
