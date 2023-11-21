package com.ibm.nlp;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.BasicAuthenticator;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;

/**
 * The Class AcdConfugurator provides a single place to configure the ACD
 * service rather than every class having to do it itself
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class AcdConfugurator {

	/** The Constant ACD_KEY. */
	private final static String ACD_KEY = "-r-wHuOSKTBALh3VvyobTaq3KEg1sr1oq4x0K7VpeU2F";

	/** The ACD BasicAuthenticator service. */
	private static AnnotatorForClinicalData service = null;

	/**
	 * Instantiates a new acd confugurator.
	 */
	public AcdConfugurator() {
	}

	/**
	 * Setup ACD.
	 *
	 * @return the annotator for clinical data
	 */
	public static AnnotatorForClinicalData getAcdServiceInstance() {
		if (service == null) {
			// each thread
			try {
				BasicAuthenticator auth = new BasicAuthenticator("apikey", ACD_KEY);
				// NoAuthAuthenticator auth = new NoAuthAuthenticator();
				service = new AnnotatorForClinicalData("2020-06-01", "ACD", auth);
				service.setServiceUrl("https://us-south.wh-acd.cloud.ibm.com/wh-acd/api");
				// service.setServiceUrl("https://watsonpow01.rch.stglabs.ibm.com/services/clinical_data_annotator/api");
				HttpConfigOptions options = new HttpConfigOptions.Builder().disableSslVerification(true).build();
				service.configureClient(options);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}

			if (service == null) {
				System.out.println("Failed to start ACD - exiting");
				System.exit(1);

			}
		}
		return service;
	}

}
