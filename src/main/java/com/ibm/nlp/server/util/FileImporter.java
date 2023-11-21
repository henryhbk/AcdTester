package com.ibm.nlp.server.util;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.ibm.nlp.hibernate.NoteEventDao;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.services.NoteEventService;

public class FileImporter {

	private static NoteEventService noteEventDao = new NoteEventDao();

	private static Integer currentRowId;

	private static Set<Integer> acdRowIds = new HashSet<Integer>();

	public FileImporter() {
		super();
	}

	public static void main(String[] args) {

		for (NoteEvent noteEvent : noteEventDao.getAllNoteEventsForMimicStudy()) {
			acdRowIds.add(noteEvent.getRowId());
		}

		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get("/Users/henryhbk/Downloads/NOTEEVENTS.csv"));
			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvParser) {
				NoteEvent noteEvent = new NoteEvent();
				noteEvent.setRowId(getIntegerValue(csvRecord.get("ROW_ID")));
				currentRowId = noteEvent.getRowId();
				noteEvent.setSubjectId(getIntegerValue(csvRecord.get("SUBJECT_ID")));
				noteEvent.setHadmId(getIntegerValue(csvRecord.get("HADM_ID")));
				noteEvent.setChartdate(getDateValue(csvRecord.get("CHARTDATE")));
				noteEvent.setCategory(csvRecord.get("CATEGORY"));
				noteEvent.setCgid(getIntegerValue(csvRecord.get("CGID")));
				noteEvent.setDescription(csvRecord.get("DESCRIPTION"));
				noteEvent.setText(csvRecord.get("TEXT"));
				noteEvent.setFhirJson(null);
				noteEvent.setAcdStudyNote(acdRowIds.contains(noteEvent.getRowId()));
				noteEventDao.saveNoteEvent(noteEvent);
			}
		} catch (Exception e) {
			System.out.println("ROW ID = " + currentRowId);
			e.printStackTrace();
		}
		System.out.println("PROCEDURE IMPORT COMPLETE");
	}

	private static Integer getIntegerValue(String stringValue) {
		Integer intValue = null;
		if (stringValue != null && !stringValue.isEmpty()) {
			intValue = Integer.parseInt(stringValue);
		}
		return intValue;

	}

	private static Date getDateValue(String stringValue) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.US);

		return formatter.parse(stringValue);
	}

}
