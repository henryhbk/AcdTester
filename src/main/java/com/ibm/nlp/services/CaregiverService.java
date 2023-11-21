package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.Caregiver;

/**
 * The Interface CaregiverService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface CaregiverService {

	/**
	 * Gets the annotation.
	 *
	 * @param id the id
	 * @return the annotation
	 */
	Caregiver getCaregiver(Integer id);

	/**
	 * Gets the all annotations.
	 *
	 * @return the all annotations
	 */
	List<Caregiver> getAllCaregivers();

	/**
	 * Save annotation.
	 *
	 * @param caregiver the caregiver
	 * @return the annotation
	 */
	Caregiver saveCaregiver(Caregiver caregiver);

	/**
	 * Delete annotation.
	 *
	 * @param id the id
	 */
	void deleteCaregiver(Integer id);

}
