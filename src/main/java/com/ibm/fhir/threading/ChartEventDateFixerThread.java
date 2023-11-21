package com.ibm.fhir.threading;

import java.util.Date;
import java.util.Map;

import com.ibm.nlp.model.mimic3.Admission;
import com.ibm.nlp.model.mimic3.ChartEvent;
import com.ibm.nlp.model.mimic3.LabItem;
import com.ibm.nlp.server.util.MimicDateCleaner;
import com.ibm.nlp.services.ChartEventService;

public class ChartEventDateFixerThread implements Runnable {
	
	/** The thread name. */
	private final String threadName;
	
	/** The admission year map. */
	private final Map<Integer, Integer> admissionYearMap;
	
	/** The admission map. */
	private final Map<Integer, Admission> admissionMap;
	
	/** The lab item map. */
	private final Map<Integer, LabItem> labItemMap;
	
	/** The lab dao. */
	private final ChartEventService chartEventDao;
	
	/** The now. */
	private static Date now = new Date();
	
	/** The page. */
	private final Integer page;
	
	/**
	 * Instantiates a new lab date fixer thread.
	 *
	 * @param threadName the thread name
	 * @param admissionYearMap the admission year map
	 * @param admissionMap the admission map
	 * @param labItemMap the lab item map
	 * @param page the page
	 * @param chartEventDao the lab dao
	 */
	public ChartEventDateFixerThread(String threadName, Map<Integer, Integer> admissionYearMap,
	    Map<Integer, Admission> admissionMap, Map<Integer, LabItem> labItemMap, Integer page,
	    ChartEventService chartEventDao) {
		super();
		this.threadName = threadName;
		this.admissionYearMap = admissionYearMap;
		this.admissionMap = admissionMap;
		this.page = page;
		this.chartEventDao = chartEventDao;
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
	@SuppressWarnings("deprecation")
	private void fixTheLabPage() {
		
		System.out.println("STARTING PAGE: " + page);
		for (ChartEvent chartEvent : chartEventDao.getChartEventsByPage(page, MimicDateCleaner.PAGESIZE)) {
			if (admissionYearMap.containsKey(chartEvent.getHadmId())) {
				if (chartEvent.getChartTime() != null) {
					chartEvent.getChartTime().setYear(admissionYearMap.get(chartEvent.getHadmId()));
				}
				if (chartEvent.getStoreTime() != null) {
					chartEvent.getStoreTime().setYear(admissionYearMap.get(chartEvent.getHadmId()));
				}
				chartEventDao.saveOrUpdateChartEvent(chartEvent);
			}
		}
		System.out.println("***** COMPLETED PAGE: " + page);
	}
	
}
