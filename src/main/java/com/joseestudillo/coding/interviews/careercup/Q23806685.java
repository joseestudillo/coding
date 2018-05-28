package com.joseestudillo.coding.interviews.careercup;

/**
 * 23806685 @ CareerCup
 *
 * @author Jose Estudillo
 */
public class Q23806685 {

    /*
     * We have two strings: a normal alphanumeric string and a pattern string. the pattern string can be composed by alphanumeric chars plus the char "?" and
     * "*"
     * 
     * We want to check if the first string match the pattern, where the ? means that every char (alphanumeric) is permitted in that position, while * allows to
     * have a sequence of alphanumeric chars.
     * 
     * 
     * as test we want to check that the function returns true to the following calls.
     * 
     * isMatching("abab","abab")
     * isMatching("abab","a**b")
     * isMatching("ababab","ab*b")
     * isMatching("","*")
     * isMatching("aaaaaab","*?*b")
     */

    public static final char CHAR = '?';
    public static final char CHAR_SEQ = '*';

    public static boolean isMatching(String input, String pattern) {
        return checkMatch(input, pattern);
    }

    private static boolean checkMatch(String input, String pattern) {
        int inputSize = input.length();
        int patternSize = pattern.length();

        boolean stop = ((inputSize == 0 && patternSize != 0) || (inputSize != 0 && patternSize == 0));
        if(stop) {
            return false;
        }

        boolean match = (inputSize == 0 && patternSize == 0); // if we can consume both strings at the same time is a match
        if(match) {
            return true;
        }
        // check more things
        if(!match) {
            if(input.charAt(0) == pattern.charAt(0)) {
                return checkMatch(input.substring(1), pattern.substring(1));
            } else if(pattern.charAt(0) == CHAR) {
                return checkMatch(input.substring(1), pattern.substring(1));
            } else if(pattern.charAt(0) == CHAR_SEQ) {
                pattern = pattern.substring(1);
                while(input.length() > 0) {
                    input = input.substring(1);
                    if(checkMatch(input, pattern)) { // if I get a working match just return the string
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println(isMatching("abab", "abab"));
        System.out.println(isMatching("abab", "a**b"));
        System.out.println(isMatching("ababab", "ab*b"));
        System.out.println(isMatching("", "*"));
        System.out.println(isMatching("aaaaaab", "*?*b"));
    }
}
