package com.joseestudillo.coding.interviews;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array left shift
 *
 * @author Jose Estudillo
 */
public class ArrayLeftShift {

    public static int[] leftShift(int[] a, int n) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[(i + n) % a.length];
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(leftShift(new int[0], 1)));
        for (int i = 0; i < 5; i++) {
            int[] a = IntStream.rangeClosed(0, i).toArray();
            for (int shift = 0; shift < a.length; shift++) {
                System.out.println(Arrays.toString(leftShift(a, shift)));
            }
        }
    }
}
