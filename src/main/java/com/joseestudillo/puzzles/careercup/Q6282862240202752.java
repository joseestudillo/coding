package com.joseestudillo.puzzles.careercup;

import java.util.Arrays;

public class Q6282862240202752 {

    /*
     * Given an integer array. Perform circular right shift by n.
     * Give the best solution.
     * http://www.careercup.com/question?id=6282862240202752
     */

    // not considering negatives times = Math.abs(times % input.length);

    public static int[] solution(int[] input, int times) {
        times = times % input.length;
        int[] result = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            result[(i + times) % input.length] = input[i];
        }
        return result;
    }

    // quick test
    public static void main(String[] args) {
        int[] input = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        for(int i = 0; i < input.length; i++) {
            System.out.println(Arrays.toString(solution(input, i)));
        }
    }

}
