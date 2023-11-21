package com.ibm.fhir.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.ibm.nlp.model.mimic3.MedicationAdverseEvent;

public class BratUtil {

	public static void exportADEToBrat(List<MedicationAdverseEvent> noteEventList) {
		FileWriter writer;
		// create the annotation configuration file
		try {
			writer = new FileWriter("annotation.conf");
			writer.write("[entities]\n" + "MedicationAdverseEvent\n" + "FalsePositive\n" + "[events]\n"
					+ "[attributes]\n[relations]\n");
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		System.out.println("Writing note annotation files");
		for (MedicationAdverseEvent noteEvent : noteEventList) {
			String fileName = "brat/" + noteEvent.getHadmId();
			try {
				writer = new FileWriter(fileName + ".txt");
				// writer.write(noteEvent.getText());
				writer.close();
				writer = new FileWriter(fileName + ".ann");
				// writer.write(noteEvent.ToBratAnnotation());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Completed note annotation files");

	}

}
