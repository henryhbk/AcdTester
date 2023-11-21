package com.ibm.nlp.server.util;

import java.text.SimpleDateFormat;

import com.ibm.nlp.hibernate.NoteEventDao;
import com.ibm.nlp.hibernate.PatientDao;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.services.NoteEventService;
import com.ibm.nlp.services.PatientService;

/**
 * The Class ServiceSandbox is just a basica class to play with the services to
 * look at the database.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class ServiceSandbox {

	private static String beginning = "Date of Birth:  [**";
	private static String end = "**]";

	private static Integer startValue, endValue;

	private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");

	/** The Constant medicationAdverseEventDao. */
	@SuppressWarnings("unused")
	private static final NoteEventService noteEventDao = new NoteEventDao();

	private static final PatientService patientDao = new PatientDao();

	public static void main(String[] args) {

		System.out.println(patientDao.getPatient(64));

		NoteEvent noteEvent = noteEventDao.getNoteEvent(1);
		Patients patient = patientDao.getPatientBySubjectId(noteEvent.getSubjectId());

		// System.out.println(noteEvent.getText());
		System.out.println("Beginning Index: " + noteEvent.getText().indexOf(beginning));
		System.out.println("end Index: " + noteEvent.getText().indexOf(end));
		if (noteEvent.getText().indexOf(beginning) >= 0 && noteEvent.getText().indexOf(end) >= 0) {
			startValue = noteEvent.getText().indexOf(beginning);
			endValue = noteEvent.getText().indexOf(end);
			System.out.println("startValue = " + startValue);
			System.out.println("endValue = " + endValue);
			String start = noteEvent.getText().substring(0, noteEvent.getText().indexOf(beginning));

			String finish = noteEvent.getText().substring(endValue + 3);

			noteEvent.setText(start + "Date Of Birth: " + formatter.format(patient.getDob()) + finish);
			System.out.println(noteEvent.getText());
		}

	}

}
