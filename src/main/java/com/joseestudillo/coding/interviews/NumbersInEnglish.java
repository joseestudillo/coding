package com.joseestudillo.coding.interviews;

/**
 * Facebook question for Data Engineer. How to read numbers in english.
 * <p>
 * I'm not considering limitations when you pass the trillion.
 *
 * @author Jose Estudillo
 */
public class NumbersInEnglish {


	private static String[] suffixes = {"", "thousand", "million", "billion", "trillion"};
	private static String[] oneDigitStrs = {"zero", "one", "two", "tree", "four", "five", "six", "seven", "eight", "nine"};
	private static String[] twoDigitStrs = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

	public static String toEnglish(int n) {
		StringBuilder sb = new StringBuilder();
		int tripletProcessed = 0;
		while (n != 0) {
			int toProcess = n % 1000; //get the last 3 digits or less
			//add smarter logic for the spaces
			if (sb.length() > 0) {
				sb.insert(0, " ");
			}
			sb.insert(0, suffixes[tripletProcessed]);
			sb.insert(0, " ");
			sb.insert(0, translate3Digits(toProcess));
			n = n / 1000; // remove the last 3 digits.
			tripletProcessed++; // increase the number of '3 digits' you have processed so far.
		}
		return sb.toString();
	}

	private static String translate3Digits(int n) {
		int firstDigit = n / 100;
		int secondDigit = (n % 100) / 10;
		int thirdDigit = n % 10;

		StringBuilder sb = new StringBuilder();
		if (firstDigit != 0) {
			sb.append(oneDigitStrs[firstDigit]);
			sb.append(" hundred");
		}
		if (secondDigit == 1) {
			if (thirdDigit == 1) {
				sb.append(" eleven");
			} else if (thirdDigit == 2) {
				sb.append(" twelve");
			} else {
				sb.append(" ");
				sb.append(oneDigitStrs[thirdDigit]);
				sb.append("teen");
			}
		} else {
			sb.append(" ");
			sb.append(twoDigitStrs[secondDigit]);
			sb.append(oneDigitStrs[thirdDigit]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		int[] toTry = {1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111, 1111111111};
		for (int n : toTry) {
			System.out.println(String.format("%s -> %s", n, toEnglish(n)));
		}
	}
}