package com.ibm.fhir.util;

import java.util.UUID;

/**
 * The Class CareGiverUtil.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class CareGiverUtil {

	/**
	 * Generates a random NPI number (11 digit string) for simulating CMS's NPI
	 * number for a physician.
	 *
	 * @return the random NPI
	 */
	public static String getRandomNPI() {

		UUID uuid = UUID.randomUUID();
		Long lowBits = uuid.getLeastSignificantBits();
		String NPI = lowBits.toString().substring(lowBits.toString().length() - 11);
		return NPI;
	}

}
