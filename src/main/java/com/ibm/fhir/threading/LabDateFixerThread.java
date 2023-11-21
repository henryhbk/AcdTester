package com.ibm.fhir.threading;

import java.util.Date;
import java.util.Map;

import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.hibernate.LabEventDao;
import com.ibm.nlp.model.mimic3.Admission;
import com.ibm.nlp.model.mimic3.LabEvent;
import com.ibm.nlp.model.mimic3.LabItem;
import com.ibm.nlp.server.util.MimicDateCleaner;

/**
 * The Class LabDateFixerThread is a thread that.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@SuppressWarnings("unused")
public class LabDateFixerThread implements Runnable {

	/** The thread name. */
	private final String threadName;

	/** The admission year map. */
	private final Map<Integer, Integer> admissionYearMap;

	/** The admission map. */
	private final Map<Integer, Admission> admissionMap;

	/** The lab item map. */
	private final Map<Integer, LabItem> labItemMap;

	/** The lab dao. */
	private final LabEventDao labDao;

	/** The now. */
	private static Date now = new Date();

	/** The page. */
	private final Integer page;

	/**
	 * Instantiates a new lab date fixer thread.
	 *
	 * @param threadName       the thread name
	 * @param admissionYearMap the admission year map
	 * @param admissionMap     the admission map
	 * @param labItemMap       the lab item map
	 * @param page             the page
	 * @param labDao           the lab dao
	 */
	public LabDateFixerThread(String threadName, Map<Integer, Integer> admissionYearMap,
			Map<Integer, Admission> admissionMap, Map<Integer, LabItem> labItemMap, Integer page, LabEventDao labDao) {
		super();
		this.threadName = threadName;
		this.admissionYearMap = admissionYearMap;
		this.admissionMap = admissionMap;
		this.page = page;
		this.labDao = labDao;
		this.labItemMap = labItemMap;
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		fixTheLabPage();
	}

	/**
	 * Fix the lab page runs a given lab event page and sets the dates to fixed.
	 */
	private void fixTheLabPage() {

		System.out.println("STARTING PAGE: " + page);
		for (LabEvent labEvent : labDao.getLabEventsByPage(page, MimicDateCleaner.PAGESIZE)) {
			if (labEvent.getValue() != null && !labEvent.getValue().isEmpty() && labEvent.getFhirJson() == null) {
				labEvent.setFhirJson(FhirUtil.getLabAsFhir(labEvent, labItemMap.get(labEvent.getItemid())));
				if (labEvent.getFhirJson() != null) {
					labDao.saveLabEvent(labEvent);
				}
			}
		}
		System.out.println("***** COMPLETED PAGE: " + page);

	}
}
