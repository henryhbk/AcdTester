package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.EKGReport;
import com.ibm.nlp.model.mimic3.EchoReport;
import com.ibm.nlp.model.mimic3.RadiologyReport;

/**
 * The Interface ReportService are the various specialty reports (radiology, EKG
 * and echo).
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface ReportService {

	/**
	 * Gets the radiology report.
	 *
	 * @param id the id
	 * @return the radiology report
	 */
	RadiologyReport getRadiologyReport(Integer id);

	/**
	 * Gets the ekg report.
	 *
	 * @param id the id
	 * @return the ekg report
	 */
	EKGReport getEkgReport(Integer id);

	/**
	 * Gets the echo report.
	 *
	 * @param id the id
	 * @return the ekg report
	 */
	EchoReport getEchoReport(Integer id);

	/**
	 * Gets the all radiology reports.
	 *
	 * @return the all radiology reports
	 */
	List<RadiologyReport> getAllRadiologyReports();

	/**
	 * Gets the all EKG reports.
	 *
	 * @return the all EKG reports
	 */
	List<EKGReport> getAllEKGReports();

	/**
	 * Gets the all Echo reports.
	 *
	 * @return the all EKG reports
	 */
	List<EchoReport> getAllEchoReports();

	/**
	 * Gets the all EKG reports for mimic study.
	 *
	 * @return the all EKG reports for mimic study
	 */
	List<EKGReport> getAllEKGReportsForMimicStudy();

	/**
	 * Gets the all radiology reports for mimic study.
	 *
	 * @return the all radiology reports for mimic study
	 */
	List<RadiologyReport> getAllRadiologyReportsForMimicStudy();

	/**
	 * Gets the all radiology reports for mimic study.
	 *
	 * @return the all radiology reports for mimic study
	 */
	List<EchoReport> getAllEchoReportsForMimicStudy();

	/**
	 * Save radiology report.
	 *
	 * @param radiologyReport the radiology report
	 * @return the radiology report
	 */
	RadiologyReport saveRadiologyReport(RadiologyReport radiologyReport);

	/**
	 * Save EKG report.
	 *
	 * @param ekgReport the ekg report
	 * @return the EKG report
	 */
	EKGReport saveEKGReport(EKGReport ekgReport);

	/**
	 * Save EKG report.
	 *
	 * @param echoReport the echo report
	 * @return the EKG report
	 */
	EchoReport saveEchoReport(EchoReport echoReport);

	/**
	 * Delete EKG report.
	 *
	 * @param id the id
	 */
	void deleteEKGReport(Integer id);

	/**
	 * Delete EKG report.
	 *
	 * @param id the id
	 */
	void deleteEchoReport(Integer id);

	/**
	 * Delete radiology report.
	 *
	 * @param id the id
	 */
	void deleteRadiologyReport(Integer id);

	/**
	 * Search radiology reports for text.
	 *
	 * @param text the text
	 * @return the list
	 */
	List<RadiologyReport> searchRadiologyReportsForText(String text);

	/**
	 * Search EKG report reports for text.
	 *
	 * @param text the text
	 * @return the list
	 */
	List<EKGReport> searchEKGReportReportsForText(String text);

	/**
	 * Search EKG report reports for text.
	 *
	 * @param text the text
	 * @return the list
	 */
	List<EchoReport> searchEchoReportReportsForText(String text);

	/**
	 * Gets the l echo report by page.
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the l echo report by page
	 */
	List<EchoReport> getEchoReportByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the EKG report by page.
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the EKG report by page
	 */
	List<EKGReport> getEKGReportByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the radiology report by page.
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the radiology report by page
	 */
	List<RadiologyReport> getRadiologyReportByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the number of echo report rows for paging.
	 *
	 * @return the number of echo report rows
	 */
	Long getNumberOfEchoReportRows();

	/**
	 * Gets the number of EKG report rows for paging.
	 *
	 * @return the number of EKG report rows
	 */
	Long getNumberOfEKGReportRows();

	/**
	 * Gets the number of radiology report rows for paging.
	 *
	 * @return the number of radiology report rows
	 */
	Long getNumberOfRadiologyReportRows();

	/**
	 * Gets the number of null radiology report rows.
	 *
	 * @return the number of null radiology report rows
	 */
	Long getNumberOfNullRadiologyReportRows();

	/**
	 * Gets the number of null echo report rows.
	 *
	 * @return the number of null echo report rows
	 */
	Long getNumberOfNullEchoReportRows();

	/**
	 * Gets the all echo reports for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all echo reports for patient
	 */
	List<EchoReport> getAllEchoReportsForPatient(Integer subjectId);

	/**
	 * Gets the all EKG reports for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all EKG reports for patient
	 */
	List<EKGReport> getAllEKGReportsForPatient(Integer subjectId);

	/**
	 * Gets the all radiology reports for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all radiology reports for patient
	 */
	List<RadiologyReport> getAllRadiologyReportsForPatient(Integer subjectId);

	/**
	 * Gets the all null radiology reports for patient by page.
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the all null radiology reports for patient by page
	 */
	List<RadiologyReport> getAllNullRadiologyReportsForPatientByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the all null radiology reports for patient by page.
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the all null radiology reports for patient by page
	 */
	List<EchoReport> getAllNullEchoReportsForPatientByPage(Integer pageNumber, Integer pageSize);
}
