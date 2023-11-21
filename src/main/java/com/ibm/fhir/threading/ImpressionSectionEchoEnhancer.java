package com.ibm.fhir.threading;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ibm.cloud.sdk.core.service.exception.BadRequestException;
import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.model.mimic3.EchoReport;
import com.ibm.nlp.server.util.MimicDateCleaner;
import com.ibm.nlp.services.ReportService;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;
import com.ibm.watson.health.acd.v1.model.ContainerGroup;
import com.ibm.watson.health.acd.v1.model.SectionAnnotation;

public class ImpressionSectionEchoEnhancer implements Runnable {

	/** The thread name. */
	private final String threadName;

	private final ReportService reportsDao;

	private final AnnotatorForClinicalData service;

	/** The page. */
	private final Integer page;

	private final Set<String> sectionNames = new HashSet<String>();

	public ImpressionSectionEchoEnhancer(String threadName, ReportService reportsDao, AnnotatorForClinicalData service,
			Integer page) {
		super();
		this.threadName = threadName;
		this.reportsDao = reportsDao;
		this.service = service;
		this.page = page;
		sectionNames.add("IMPRESSION");
		sectionNames.add("PFI REPORT");
		sectionNames.add("RADLINE");
		sectionNames.add("FINAL REPORT");
		sectionNames.add("CONCLUSIONS");
		sectionNames.add("INTERPRETATION");

	}

	@Override
	public void run() {

		System.out.println(threadName + " thread starting");

		List<EchoReport> reports = reportsDao.getAllNullEchoReportsForPatientByPage(page, MimicDateCleaner.PAGESIZE);

		for (EchoReport report : reports) {
			try {
				// makes restarts very fast as we ignore already completed records
				if (report.getImpression() == null || report.getImpression().isEmpty()) {
					doNLPViaACD(report);
				}
			} catch (Exception e) {
				System.out.println(threadName + " crashed");
				e.printStackTrace();
			}
		}
		System.out.println(threadName + " exiting");

	}

	private synchronized void doNLPViaACD(final EchoReport report) throws Exception {

		// ACD response object
		ContainerGroup response = null;

		// wh_acd.ibm_clinical_insights_v1.0_standard_flow
		try {
			response = service.analyzeWithFlow("henry_test_cartridge_v1.0_aap_test_flow", report.getText());
			System.out.println(page + " ACD query completed");
		} catch (BadRequestException e) {
			java.lang.System.out.println(e.getMessage() + " " + e.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SectionAnnotation> listSections = response.getSectionAnnotations();
		String impressionText = null;
		if (listSections != null) {
			// Example of fetching values from a concept in the list of concepts
			int counter = 0;
			for (SectionAnnotation concept : listSections) {
				// Get the A&P section and get it's text as then we will be parsing that into a
				// list of conditions to then look inside
				if (sectionNames.contains((concept.getTrigger().getSectionNormalizedName().toUpperCase()))) {
					Long begin = concept.getTrigger().getEnd();
					begin = begin + 1L; // offset the : at the end of the name
					// if A&P is the last section then we need to do a different range
					Long end;
					if (listSections.size() == counter) {
						SectionAnnotation nextAnnotation = listSections.get(counter + 1);
						end = nextAnnotation.getTrigger().getEnd();
					} else {
						end = new Long(report.getText().length());
					}
					// get the substring that is the A&P section
					impressionText = report.getText().substring(begin.intValue(), end.intValue());
				}
				counter++;
			}

			// SO now we can have the plan into a hashmap based on their covered texts from
			// the diagnosis list from the cartridge. This is because ACD currently doesn't
			// pull the associated plan with the problems
			if (impressionText == null || impressionText.isEmpty()) {
				report.setImpression(null);
				System.out.println(report.getText());
			} else {
				report.setImpression(StringUtils.left(impressionText, 2047));
			}

			FhirUtil.getEchoReportAsFhir(report);
			reportsDao.saveEchoReport(report);
		}
	}

}
