package com.joseestudillo.coding.interviews.careercup.facebook;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created on 06/09/2017.
 *
 * @author Jose Estudillo
 */

/*

For given list of numbers find out triplets with sum 0
Input : arr[] = {0, -1, 2, -3, 1}
Output : 0 -1 1
2 -3 1

*/

public class Q5185811961085952 {

	public static void triplets(int[] array, int sum) {
		niplets(array, 0, new int[3], 0);
	}

	private static int sum(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	private static void niplets(int[] array, int ai, int[] candidate, int ci) {
		if (ci < candidate.length) {
			if(ai < array.length) {
				candidate[ci] = array[ai];
				niplets(array, ai + 1, candidate, ci + 1);
				niplets(array, ai + 1, candidate, ci);
			}
		} else {
			if (sum(candidate) == 0) {
				System.out.println(Arrays.toString(candidate));
			}
		}
	}

	public static void main(String[] args) {
		int[] input = {0, -1, 2, -3, 1};
		//Output : 0 -1 1
		//2 -3 1

		triplets(input, 0);
	}
}
