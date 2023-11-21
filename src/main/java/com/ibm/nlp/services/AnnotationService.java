package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.Annotation;

// TODO: Auto-generated Javadoc
/**
 * The Interface AnnotationService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface AnnotationService {

	/**
	 * Gets the annotation.
	 *
	 * @param id the id
	 * @return the annotation
	 */
	Annotation getAnnotation(Integer id);

	/**
	 * Gets the all annotations.
	 *
	 * @return the all annotations
	 */
	List<Annotation> getAllAnnotations();

	/**
	 * Save annotation.
	 *
	 * @param annotation the annotation
	 * @return the annotation
	 */
	Annotation saveAnnotation(Annotation annotation);

	/**
	 * Delete annotation.
	 *
	 * @param id the id
	 */
	void deleteAnnotation(Integer id);

}
