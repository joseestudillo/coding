package com.joseestudillo.puzzles;

import java.io.PrintStream;

public class Palindrome {

    public static String reverse(String input) {
        if(input == null) {
            return null;
        }
        char[] array = input.toCharArray();
        char tmp;
        for(int i = 0, j = array.length - 1; i < array.length / 2; i++, j--) {
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

    // quick test. Ideally do a JUnit test
    public static void main(String[] args) {
        PrintStream out = System.out;

        out.println(reverse("hola"));
        out.println(reverse(""));
        out.println(isPalidrome("holaaloh"));
        out.println(isPalidromeRecursive("holaaloh"));
    }
}
