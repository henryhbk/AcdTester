/**
 * 
 */
package com.ibm.nlp.hibernate.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ibm.nlp.hibernate.ReportsDao;
import com.ibm.nlp.model.mimic3.EchoReport;
import com.ibm.nlp.services.ReportService;

/**
 * @author henryhbk
 *
 */
class ReportDaoTest {
	
	private static ReportService reportDao;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		reportDao = new ReportsDao();
		assertNotEquals(null, reportDao);
	}
	
	@Test
	void createThenDeleteAnEchoReportTest() {

		final  int TESTID = 9999999;
		
		EchoReport report = new EchoReport();
		report.setRowId(TESTID);
		report.setCategory("JUNIT TEST REPORT");
		report.setCgid(null);
		report.setChartdate(new Date());
		report.setDescription("JUNIT");
		report.setHadmId(0);
		report.setAcdStudyNote(false);
		report.setSubjectId(3);
		report.setText("THIS IS TEST TEXT");
		report.setFhirJson("{JSON TEST}");
		
		reportDao.saveEchoReport(report);

		
		EchoReport testReport = reportDao.getEchoReport(TESTID);
		//retrieve the one we just got and check the ID
		assertEquals(testReport.getRowId(), TESTID);
		
		reportDao.deleteEchoReport(TESTID);
		EchoReport nullReport = reportDao.getEchoReport(TESTID);
		
		assertEquals(nullReport, null);


	}
	
}
