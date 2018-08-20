package com.joseestudillo.coding.interviews.ctci;

/**
 * One Away: There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 */
public class IQ_1_5 {

    //this doesnt consider replacements when you edit
    public static int changes(String input, String edit) {
        if (input.length() != edit.length()) {
            return Math.abs(input.length() - edit.length());
        }
        char[] inputChars = input.toCharArray();
        char[] editChars = edit.toCharArray();
        int changes = 0;
        for (int i = 0; i < inputChars.length; i++) {

        }
        return changes;
    }


}
