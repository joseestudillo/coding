package com.joseestudillo.coding.interviews.ctci;

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
        while (i < input.length()) {
            int tmpCount = 0;
            char currentChar = input.charAt(i);
            do {
                tmpCount++;
                i++;
            } while (i < input.length() && currentChar == input.charAt(i));
            result += String.format("%s%s", currentChar, tmpCount > 1 ? tmpCount : "");
        }
        return result;
    }

    public static String anotherCompression(String input) {
        StringBuilder sb = new StringBuilder();
        if (input.length() > 0) {
            char lc = input.charAt(0);
            int lcc = 1;
            for (int i = 1; i < input.length(); i++) {
                char cc = input.charAt(i);
                if (cc == lc) {
                    lcc++;
                } else {
                    sb.append(String.format("%s%s", lc, lcc > 1 ? lcc : ""));
                    lc = cc;
                    lcc = 1;
                }
            }
            sb.append(String.format("%s%s", lc, lcc > 1 ? lcc : ""));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(anotherCompression(""));
        System.out.println(anotherCompression("a"));
        System.out.println(anotherCompression("abc"));
        System.out.println(anotherCompression("aabcccccaaa"));
    }


}
