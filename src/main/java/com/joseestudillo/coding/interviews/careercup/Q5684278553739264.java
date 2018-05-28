package com.joseestudillo.coding.interviews.careercup;


/**
 * 5684278553739264 in CareerCup
 *
 * @author Jose Estudillo
 */
public class Q5684278553739264 {

    /*
     * Given s string, Find max size of a sub-string, in which no duplicate chars present
     * http://www.careercup.com/question?id=5684278553739264
     */

    // TODO Improvements
    // stop when the rest of the string is shorter that the current solution

    public static int solution(String input) {
        int max = 0;
        String substring;
        String tmp;
        for(int i = 0; i < input.length(); i++) {
            substring = "";
            for(int j = i; j < input.length(); j++) {
                tmp = String.valueOf(input.charAt(j));
                if(substring.contains(tmp)) {
                    i = input.indexOf(tmp, i) + 1;
                    // start after the previous occurrence of the repeated character
                    break;
                } else {
                    substring += tmp;
                }
            }
            max = Math.max(max, substring.length());
        }
        return max;
    }

    // quick test
    public static void main(String[] args) {
        String message = "The solution for %s is %s";
        String[] strings = new String[] { "", "a", "aa", "murcielago", "patata" };
        for(String input : strings) {
            System.out.println(String.format(message, input, solution(input)));
        }
    }

}
