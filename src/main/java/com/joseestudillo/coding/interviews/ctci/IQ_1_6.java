package com.joseestudillo.coding.interviews.ctci;

import static scala.runtime.BoxesRunTime.add;

/**
 * String Compression: Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
 * "compressed" string would not become smaller than the original string, your method should return
 * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 */
public class IQ_1_6 {

    public static String compression(String input) {
        int i = 0;
        String result = ""; //string builder
        while(i < input.length()) {
            int tmpCount = 0;
            char currentChar = input.charAt(i);
            do {
                tmpCount++;
                i++;
            } while (i < input.length() && currentChar == input.charAt(i));
            result += String.format("%s%s",currentChar, tmpCount > 1 ? tmpCount : "");
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(compression(""));
        System.out.println(compression("a"));
        System.out.println(compression("abc"));
        System.out.println(compression("aabcccccaaa"));
    }


}
