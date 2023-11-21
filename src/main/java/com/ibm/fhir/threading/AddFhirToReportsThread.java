package com.ibm.fhir.threading;

import java.util.Map;

import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.model.mimic3.EKGReport;
import com.ibm.nlp.server.util.MimicDateCleaner;
import com.ibm.nlp.services.ReportService;

public class AddFhirToReportsThread implements Runnable {

	/** The thread name. */
	private final String threadName;

	private final ReportService reportsDao;

	/** The admission year map. */
	private final Map<Integer, Integer> admissionYearMap;

	/** The page. */
	private final Integer page;

	public AddFhirToReportsThread(String threadName, Integer page, ReportService reportsDao,
			Map<Integer, Integer> admissionYearMap) {
		super();
		this.threadName = threadName;
		this.reportsDao = reportsDao;
		this.page = page;
		this.admissionYearMap = admissionYearMap;
	}

	@Override
	public void run() {
		fixReportPage();
	}

	@SuppressWarnings("deprecation")
	private void fixReportPage() {
		System.out.println("Starting thread " + threadName);
		for (EKGReport report : reportsDao.getEKGReportByPage(page, MimicDateCleaner.PAGESIZE)) {
			if (report.getHadmId() != null && admissionYearMap.containsKey(report.getHadmId())) {
				report.getChartdate().setYear(admissionYearMap.get(report.getHadmId()));
				FhirUtil.getEKGReportAsFhir(report);
				reportsDao.saveEKGReport(report);
			} else {
				Integer newYear = (int) Math.abs((Math.random()) * (90 - 120) + 120);
				report.getChartdate().setYear(newYear);
			}
		}
		System.out.println("Completed thread " + threadName);
	}

}