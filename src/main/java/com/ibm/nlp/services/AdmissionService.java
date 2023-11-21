package com.ibm.nlp.services;

import java.util.List;
import java.util.Set;

import com.ibm.nlp.model.mimic3.Admission;
import com.ibm.nlp.model.mimic3.IcuStay;
import com.ibm.nlp.model.mimic3.TransferringService;

/**
 * The Interface AdmissionService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface AdmissionService {

	/**
	 * Gets the Admission.
	 *
	 * @param id the id
	 * @return the Admission
	 */
	Admission getAdmission(Integer id);

	/**
	 * Gets the all Admission.
	 *
	 * @return the all Admission
	 */
	List<Admission> getAllAdmission();

	/**
	 * Save Admission.
	 *
	 * @param admission the admission
	 * @return the Admission
	 */
	Admission saveAdmission(Admission admission);

	/**
	 * Delete Admission.
	 *
	 * @param id the id
	 */
	void deleteAdmission(Integer id);

	/**
	 * Gets the all admission for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all admission for patient
	 */
	List<Admission> getAllAdmissionForPatient(Integer subjectId);

	/**
	 * Gets the transfering services for patient.
	 *
	 * @param subjectId the subject id
	 * @return the transferring services for patient
	 */
	List<TransferringService> getTransferringServicesForPatient(Integer subjectId);

	/**
	 * Gets the transfering services for admission.
	 *
	 * @param hadmId the hadm id
	 * @return the transfering services for admission
	 */
	List<TransferringService> getTransferringServicesForAdmission(Integer hadmId);

	/**
	 * Gets the transfering from services for service.
	 *
	 * @param serviceName the service name
	 * @return the transfering from services for service
	 */
	List<TransferringService> getTransferringFromServicesForService(String serviceName);

	/**
	 * Gets the transfering to services for service.
	 *
	 * @param serviceName the service name
	 * @return the transfering to services for service
	 */
	List<TransferringService> getTransferringToServicesForService(String serviceName);

	/**
	 * Gets the list of service names.
	 *
	 * @return the list of service names
	 */
	Set<String> getListOfServiceNames();

	/**
	 * Gets the icu stay.
	 *
	 * @param id the id
	 * @return the icu stay
	 */
	IcuStay getIcuStay(Integer id);

	/**
	 * Gets the all icu stay.
	 *
	 * @return the all icu stay
	 */
	List<IcuStay> getAllIcuStay();

	/**
	 * Save icu stay.
	 *
	 * @param admission the admission
	 * @return the icu stay
	 */
	IcuStay saveIcuStay(IcuStay icuStay);

	/**
	 * Delete icu stay.
	 *
	 * @param id the id
	 */
	void deleteIcuStay(Integer id);

	/**
	 * Gets the all icu stay for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all icu stay for patient
	 */
	List<IcuStay> getAllIcuStayForPatient(Integer subjectId);

}
