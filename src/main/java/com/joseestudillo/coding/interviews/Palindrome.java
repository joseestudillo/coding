package com.joseestudillo.coding.interviews;


/**
 * Check if a given string in a palindrome.
 *
 * @author Jose Estudillo
 */
public class Palindrome {

	public static String reverse(String input) {
		if (input == null) {
			return null;
		}
		char[] array = input.toCharArray();
		char tmp;
		for (int i = 0, j = array.length - 1; i < array.length / 2; i++, j--) {
			tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
		return new String(array);
	}

	// simple version
	public static boolean isPalidrome(String input) {
		return input.compareTo(reverse(input)) == 0;
	}

	// recursive version
	public static boolean isPalidromeRecursive(String input) {
		char[] tmp = input.toCharArray();
		return isPalidromeRecursive(tmp, 0, tmp.length - 1);
	}

	private static boolean isPalidromeRecursive(char[] input, int ini, int end) {
		return end < ini || (input[ini] == input[end] && isPalidromeRecursive(input, ++ini, --end));
	}
}
