package com.ibm.nlp.server.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * The Class UniqueRandomIdGenerator.
 *
 * @author Erfan Ahmed
 * @since 04/13/2018
 */
public class UniqueRandomIdGenerator {

	/**
	 * Letter 'O' and number 0 didn't take into account to avoid misleading
	 * interpretation.
	 */
	private static char LETTER[] = { 'A', '1', 'B', '2', 'C', '3', 'D', '4', 'E', '5', 'F', '6', 'G', '7', 'H', '8',
			'I', '9', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	/** The digits. */
	// array length needs to be longer than the current year
	private static char DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	/**
	 * prime numbers are for mod(%) operation prime number must not be greater than
	 * (LETTER.length + 1)
	 */
	private static List<Integer> PRIME_LIST = makePrimList(11, LETTER.length);

	/** The start pos. */
	private static int START_POS = 0;

	/**
	 * Gets the unique random id.
	 *
	 * @return the unique random id
	 */
	public static String getUniqueRandomId() {
		StringBuilder uniqueRandomId = new StringBuilder();

		LocalDateTime localDateTime = LocalDateTime.now();

		int year = localDateTime.getYear();
		int month = localDateTime.getMonthValue();
		int day = localDateTime.getDayOfMonth();
		int hour = localDateTime.getHour();
		int minute = localDateTime.getMinute();
		int sec = localDateTime.getSecond();
		int nanoSec = localDateTime.getNano();

		uniqueRandomId.append(DIGITS[year % getRandomPrime()]).append(DIGITS[month])
				.append(DIGITS[day % getRandomPrime()]).append(DIGITS[hour % getRandomPrime()])
				.append(DIGITS[minute < DIGITS.length ? minute : minute % getRandomPrime()])
				.append(DIGITS[sec < DIGITS.length ? sec : sec % getRandomPrime()])
				.append(DIGITS[nanoSec % getRandomPrime()]);

		return uniqueRandomId.toString();
	}

	/**
	 * Gets the unique random id.
	 *
	 * @param prefixOrSuffix the prefix or suffix
	 * @param type           the type
	 * @return the unique random id
	 */
	@SuppressWarnings("rawtypes")
	public static String getUniqueRandomId(String prefixOrSuffix, Enum type) {

		if (type == Type.PREFIX) {
			return prefixOrSuffix + "-" + getUniqueRandomId();
		} else if (type == Type.SUFFIX) {
			return getUniqueRandomId() + "-" + prefixOrSuffix;
		}

		return getUniqueRandomId();
	}

	/**
	 * Gets the unique random id.
	 *
	 * @param prefix the prefix
	 * @param suffix the suffix
	 * @return the unique random id
	 */
	public static String getUniqueRandomId(String prefix, String suffix) {
		return prefix + "-" + getUniqueRandomId() + "-" + suffix;
	}

	/**
	 * This method generates a random number position Returns the prime number of
	 * that position of PRIME_LIST.
	 *
	 * @param origin (inclusive) basically the start position of LIST which is 0
	 * @param bound  (exclusive) basically size of PRIME_LIST
	 * @return a prime number from PRIME_LIST
	 */
	private static int getPrimeNumber(int origin, int bound) {
		int position = new Random().ints(1, origin, bound).findFirst().getAsInt();

		return PRIME_LIST.get(position);
	}

	/**
	 * To mod(%) with different prime number This will add more randomness.
	 *
	 * @return a random prime number from PRIME_LIST
	 */
	private static int getRandomPrime() {
		return getPrimeNumber(START_POS, PRIME_LIST.size());
	}

	/**
	 * Make prim list.
	 *
	 * @param fromNum the from num
	 * @param toNum   the to num
	 * @return the list
	 */
	private static List<Integer> makePrimList(int fromNum, int toNum) {
		List<Integer> primeNumList = new ArrayList<Integer>();

		IntStream.rangeClosed(fromNum, toNum)
				.filter(i -> IntStream.rangeClosed(2, (int) Math.sqrt(i)).allMatch(j -> i % j != 0)).forEach(n -> {
					primeNumList.add(n);
				});

		return primeNumList;
	}

	/**
	 * The Enum Type.
	 *
	 * @author Henry Feldman
	 * @(C) IBM Watson Health 2021
	 */
	public enum Type {

		/** The prefix. */
		PREFIX,
		/** The suffix. */
		SUFFIX,
	}

	/**
	 * Gets the random NPI.
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