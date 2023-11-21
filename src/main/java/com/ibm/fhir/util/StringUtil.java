package com.ibm.fhir.util;

public class StringUtil {

	public StringUtil() {
	}

	/**
	 * Gets the NDC code padded. We do this because it is a text representation of a
	 * number, so can't use number formatting
	 *
	 * @param NDCCode   the NDC code
	 * @param padLength the pad length
	 * @return the NDC code padded
	 */
	public static String getNDCCodePadded(String NDCCode, Integer padLength) {
		try {
			if (NDCCode.length() < padLength) {
				for (int i = 0; i < padLength + 1 - NDCCode.length(); i++) {
					NDCCode = "0" + NDCCode;
				}
			}
		} catch (NullPointerException e) {
			NDCCode = "  NA  ";
		}

		return NDCCode;

	}

}
