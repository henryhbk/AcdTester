package com.ibm.nlp.server.util;

import com.ibm.nlp.hibernate.PatientDao;
import com.ibm.nlp.hibernate.PatientDb2Dao;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.services.PatientService;

/**
 * The Class Db2CopyUtil.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class Db2CopyUtil {

	/** The Constant patientDao. */
	private static final PatientService patientDao = new PatientDao();

	/** The Constant patientDb2Dao. */
	private static final PatientService patientDb2Dao = new PatientDb2Dao();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("Copying Patients from MySql to DB2");
		try {
			for (Patients patient : patientDao.getAllPatients()) {
				patientDb2Dao.savePatient(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Copying Patients from MySql to DB2");

	}

}
